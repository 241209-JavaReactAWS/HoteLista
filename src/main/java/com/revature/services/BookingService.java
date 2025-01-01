package com.revature.services;

import com.revature.daos.BookingDAO;
import com.revature.daos.PaymentDAO;
import com.revature.daos.RoomDAO;
import com.revature.models.Account;
import com.revature.models.Booking;
import com.revature.models.Room;
import com.revature.requests.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Booking> fetchAllBookingList(Integer accountId) {
        return null;
    }
    public BookingDTO addNewBooking(Integer accountId, Integer roomId) throws Exception {
        Account account = accountService.searchById(accountId);
        Optional<Room> retreiveRoom = roomDAO.findById(roomId);
        Booking newBooking = new Booking();
        newBooking.setRoom(retreiveRoom.get());
        newBooking.setAccount(account);
        Booking availableBooking  = bookingDAO.save(newBooking);

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(availableBooking.getBookingId());
        bookingDTO.setType(retreiveRoom.get().getType());
        bookingDTO.setAvailable(retreiveRoom.get().getAvailable());
        bookingDTO.setGuestCapacity(retreiveRoom.get().getGuestCapacity());
        return bookingDTO;
    }

    public BookingDTO fetchById(Integer accountId) {
        return null;
    }

    /** TODO:
     * Add Booking
     * Update Booking - incase of canceled
     * Get All
     * Get Booking
     *
     * Stretch Have it automatically check out if the check outdate has pass
     *
     * */
}
