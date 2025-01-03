package com.revature.services;

import com.revature.daos.BookingDAO;
import com.revature.daos.PaymentDAO;
import com.revature.daos.RoomDAO;
import com.revature.exceptions.booking.BookingNotCreated;
import com.revature.exceptions.booking.BookingNotFound;
import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.*;
import com.revature.requests.BookingDTO;
import com.revature.requests.FindBookingDTO;
import com.revature.requests.UpdateBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

        private final PaymentDAO paymentDAO;
        private final AccountService accountService;
        private final BookingDAO bookingDAO;
        private final RoomDAO roomDAO;

    @Autowired
    public BookingService(PaymentDAO paymentDAO, AccountService accountService, BookingDAO bookingDAO, RoomDAO roomDAO) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
        this.bookingDAO = bookingDAO;
        this.roomDAO = roomDAO;
    }

    public Double roomPricing(RoomsType roomsType){
        Double price = 0.00;

        //Checking the price based on type
        if(roomsType.equals(RoomsType.SUITE)){
            price = 200.00;
            return price;
        } else if(roomsType.equals(RoomsType.TRIPLE)){
            price = 150.00;
            return price;
        } else if(roomsType.equals(RoomsType.DOUBLE)){
            price = 100.00;
            return price;
        } else {
            price = 50.00;
            return price;
        }
    }

    public List<FindBookingDTO> fetchAllBookingList(Integer accountId) throws Exception {
        List<FindBookingDTO> allBookings = new ArrayList<>();
        Account account = accountService.searchById(accountId);
        List<Booking> bookingList = bookingDAO.findAll();
        List<Booking> filteredList = bookingList.stream()
                .filter(item -> item.getAccount().getAccountId()==accountId)
                .toList();
        if(filteredList.isEmpty()){
            throw new BookingNotFound("NO LIST OF BOOKINGS FOUND");
        } else {
            for (Booking item: filteredList){
                FindBookingDTO findBookingDTO = new FindBookingDTO();
                findBookingDTO.setBookingId(item.getBookingId());
                findBookingDTO.setStatus(item.getStatus());
                findBookingDTO.setTotalPrice(item.getTotalPrice());
                findBookingDTO.setLengthOfStay(item.getLengthOfStay());
                allBookings.add(findBookingDTO);
            }
            return allBookings;
        }
    }

    public BookingDTO addNewBooking(Booking booking, Integer accountId, Integer roomId,Integer paymentId) throws Exception {
        //Find account by id
        Account account = accountService.searchById(accountId);
        //Find room by id
        Optional<Room> retreiveRoom = roomDAO.findById(roomId);
        //Finding payment by id
        Optional<Payment> retrievedPayment = paymentDAO.findById(paymentId);

        if (retrievedPayment.isEmpty()) {
            throw new BookingNotCreated("PAYMENT FAILED");
        } else {

            //Calling roomPricing method to get pricing for room by type
            Double price = this.roomPricing(retreiveRoom.get().getType());

            //Updating the room to show that it is reserved
            Room newRoom = new Room();
            newRoom.setGuestCapacity(retreiveRoom.get().getGuestCapacity());
            newRoom.setRoomId(retreiveRoom.get().getRoomId());
            newRoom.setHotel(retreiveRoom.get().getHotel());
            newRoom.setType(retreiveRoom.get().getType());
            newRoom.setAvailable(0);

            //Creating a new booking
            Booking newBooking = new Booking();
            newBooking.setAccount(account);
            newBooking.setStatus(BookingStatus.COMPLETE_PAYMENT);
            newBooking.setLengthOfStay(booking.getLengthOfStay());
            newBooking.setPayment(retrievedPayment.get());
            newBooking.setTotalPrice(price);
            newBooking.setRoom(newRoom);

            //Converting single booking to a list to format into setter for room
            List<Booking> setBookingToList = new ArrayList<>();
            setBookingToList.add(newBooking);
            newRoom.setBookings(setBookingToList);

            //Saving new room and booking
            Room savedRoom = roomDAO.save(newRoom);
            Booking savedBooking = bookingDAO.save(newBooking);

            //Converting model to DTO
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setBookingId(savedBooking.getBookingId());
            bookingDTO.setStatus(savedBooking.getStatus());
            bookingDTO.setTotalPrice(savedBooking.getTotalPrice());
            bookingDTO.setLengthOfStay(savedBooking.getLengthOfStay());
            bookingDTO.setType(savedRoom.getType());
            bookingDTO.setCardHolderName(retrievedPayment.get().getCardHolderName());
            bookingDTO.setCardNumber(retrievedPayment.get().getCardNumber());

            return bookingDTO;
        }
    }

    public Booking fetchById(Integer accountId , Integer bookingId) throws Exception {
        Booking findBooking = new Booking();
        Account account = accountService.searchById(accountId);
        Optional<Booking> retreiveBooking = bookingDAO.findById(bookingId);
        if(retreiveBooking.isEmpty()){
            throw new BookingNotFound("BOOKING NOT FOUND");
        }else{
            findBooking.setAccount(retreiveBooking.get().getAccount());
            findBooking.setRoom(retreiveBooking.get().getRoom());
            findBooking.setStatus(retreiveBooking.get().getStatus());

            return findBooking;
        }
    }

    public UpdateBookingDTO updateBooking(Integer accountId , Integer bookingId) throws Exception {
        Booking updateBooking = new Booking();
        Room updateRoom = new Room();
        //Finding account information
        Account account = accountService.searchById(accountId);
        //Finding booking information
        Optional<Booking> retreiveBooking = bookingDAO.findById(bookingId);
        if (retreiveBooking.isEmpty()){
            throw new BookingNotFound("BOOKING NOT FOUND");
        }else{

            //Updating booking
            updateBooking.setAccount(account);
            updateBooking.setRoom(retreiveBooking.get().getRoom());
            updateBooking.setStatus(BookingStatus.CANCELED);
            updateBooking.setLengthOfStay(0);
            updateBooking.setBookingId(retreiveBooking.get().getBookingId());
            updateBooking.setTotalPrice(0.00);
            Booking updatedResult = bookingDAO.save(updateBooking);

            //Updating Room
            updateRoom.setRoomId(retreiveBooking.get().getRoom().getRoomId());
            updateRoom.setBookings(null);
            updateRoom.setAvailable(1);
            updateRoom.setGuestCapacity(retreiveBooking.get().getRoom().getGuestCapacity());
            updateRoom.setType(retreiveBooking.get().getRoom().getType());
            Room updatedRoomResult = roomDAO.save(updateRoom);

            //Using a DTO to hid unnessary information
            UpdateBookingDTO updatedBookingResult = new UpdateBookingDTO();
            updatedBookingResult.setBookingId(updateBooking.getBookingId());
            updatedBookingResult.setStatus(updateBooking.getStatus());
            updatedBookingResult.setTotalPrice(updateBooking.getTotalPrice());
            updatedBookingResult.setLengthOfStay(updateBooking.getLengthOfStay());
            updatedBookingResult.setType(updatedRoomResult.getType());
            updatedBookingResult.setAvailable(updatedRoomResult.getAvailable());

            return  updatedBookingResult;

        }
    }


}
