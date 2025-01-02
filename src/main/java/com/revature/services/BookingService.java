package com.revature.services;

import com.revature.daos.BookingDAO;
import com.revature.daos.PaymentDAO;
import com.revature.daos.RoomDAO;
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
            throw new PaymentNotFound("NO LIST OF BOOKINGS FOUND");
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
            throw new PaymentNotFound("PAYMENT FAILED");
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
    public BookingDTO fetchById(Integer accountId) {
        return null;
    }

    /** TODO:
     * Add Booking
     * Update Booking - incase of canceled

     *
     * Stretch Have it automatically check out if the check outdate has pass
     *
     * */
}
