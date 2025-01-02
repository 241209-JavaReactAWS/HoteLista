package com.revature.services;

import com.revature.daos.BookingDAO;
import com.revature.daos.PaymentDAO;
import com.revature.daos.RoomDAO;
import com.revature.exceptions.booking.BookingNotCreated;
import com.revature.exceptions.booking.BookingNotFound;
import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.*;
import com.revature.requests.BookingDTO;
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
        private final PaymentService paymentService;
    @Autowired
    public BookingService(PaymentDAO paymentDAO, AccountService accountService, BookingDAO bookingDAO, RoomDAO roomDAO, PaymentService paymentService) {
        this.paymentDAO = paymentDAO;
        this.accountService = accountService;
        this.bookingDAO = bookingDAO;
        this.roomDAO = roomDAO;
        this.paymentService = paymentService;
    }
    public List<BookingDTO> fetchAllBookingList(Integer accountId) throws Exception {
        List<BookingDTO> allBookings = new ArrayList<>();
        Account account = accountService.searchById(accountId);
        List<Booking> bookingList = bookingDAO.findAll();
        List<Booking> filteredList = bookingList.stream()
                .filter(item -> item.getAccount().getAccountId()==accountId)
                .toList();
        if(filteredList.isEmpty()){
            throw new BookingNotFound("NO LIST OF BOOKINGS FOUND");
        }else {
            for (Booking item: filteredList){
                BookingDTO bookingToDTO = new BookingDTO();
                bookingToDTO.setBookingId(item.getBookingId());
                bookingToDTO.setType(item.getRoom().getType());
                bookingToDTO.setGuestCapacity(item.getRoom().getGuestCapacity());
                allBookings.add(bookingToDTO);
            }
            return allBookings;
        }
    }
//    public BookingDTO addNewBooking(Integer accountId, Integer roomId,Integer paymentId) throws Exception {
public BookingDTO addNewBooking(Integer accountId, Integer roomId,Integer paymentId) throws Exception {

    //        add validation
        Account account = accountService.searchById(accountId);
        Optional<Room> retreiveRoom = roomDAO.findById(roomId);
//        Payment checkoutProcess = paymentService.checkoutPayment(account,paymentId);
        Optional<Payment> checkoutProcess = paymentDAO.findById(paymentId);
        if (checkoutProcess.isEmpty()) {
            throw new BookingNotCreated("PAYMENT FAILED");
        } else {
            Booking newBooking = new Booking();
            newBooking.setRoom(retreiveRoom.get());
            newBooking.setAccount(account);
            newBooking.setStatus(BookingStatus.COMPLETE_PAYMENT);
//            newBooking.setPayment(checkoutProcess.get());

//            newBooking.setPayment(checkoutProcess.get());
//        add requestbody to this
//        newBooking.setLengthOfStay(booking.getLengthOfStay());
            Booking availableBooking = bookingDAO.save(newBooking);

            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setBookingId(availableBooking.getBookingId());
            bookingDTO.setType(retreiveRoom.get().getType());
            bookingDTO.setAvailable(retreiveRoom.get().getAvailable());
            bookingDTO.setGuestCapacity(retreiveRoom.get().getGuestCapacity());

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
//            findBooking.setCheckInDate(retreiveBooking.get().getCheckInDate());
//            findBooking.setLengthOfStay(retreiveBooking.get().getLengthOfStay());
            return findBooking;
        }
    }
    public Booking fetchById(Booking booking , Integer accountId , Integer bookingId) throws Exception {
        Booking updateBooking = new Booking();
        Room updateRoom = new Room();
        Account account = accountService.searchById(accountId);
        Optional<Booking> retreiveBooking = bookingDAO.findById(bookingId);
        if (retreiveBooking.isEmpty()){
            throw new BookingNotFound("BOOKING NOT FOUND");
        }else{
            updateBooking.setAccount(retreiveBooking.get().getAccount());
            updateBooking.setRoom(retreiveBooking.get().getRoom());
            updateBooking.setStatus(BookingStatus.CANCELED);
            updateBooking.setLengthOfStay(retreiveBooking.get().getLengthOfStay());
            updateBooking.setBookingId(retreiveBooking.get().getBookingId());

            updateBooking=bookingDAO.save(retreiveBooking.get());

            updateRoom.setRoomId(retreiveBooking.get().getRoom().getRoomId());
            updateRoom.setBookings(null);
            updateRoom.setAvailable(1);
            updateRoom.setGuestCapacity(retreiveBooking.get().getRoom().getGuestCapacity());
            updateRoom.setType(retreiveBooking.get().getRoom().getType());
            roomDAO.save(updateRoom);
            return updateBooking;

        }
    }


    /** TODO:
     *
     * Update Booking - incase of canceled

     *
     * Stretch Have it automatically check out if the check outdate has pass
     *
     * */
}
