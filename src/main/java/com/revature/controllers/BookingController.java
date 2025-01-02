package com.revature.controllers;

import com.revature.daos.BookingDAO;
import com.revature.exceptions.booking.BookingNotCreated;
import com.revature.exceptions.booking.BookingNotFound;
import com.revature.exceptions.payment.PaymentNotCreated;
import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.Booking;
import com.revature.models.Payment;
import com.revature.requests.BookingDTO;
import com.revature.requests.PaymentDTO;
import com.revature.services.BookingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add/{accountId}/{roomId}/{paymentId}")
    public ResponseEntity<BookingDTO> addNewBooking(@RequestBody Booking booking, @PathVariable Integer accountId, @PathVariable Integer roomId, @PathVariable Integer paymentId){

        try{
            BookingDTO newBooking = bookingService.addNewBooking(booking, accountId, roomId, paymentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        }catch (Exception e) {
            throw new BookingNotCreated("BOOKING FAILED");
        }
    }

    @GetMapping("/fetchAllBooking/{accountId}")
    public ResponseEntity<List<BookingDTO>> fetchAlBooking(@PathVariable Integer accountId){
        try{
            List<BookingDTO> bookingList = bookingService.fetchAllBookingList(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(bookingList);
        } catch (Exception e) {
            throw new BookingNotFound("No LIST OF BOOKINGS FOUND");
        }
    }
    @GetMapping("/fetchById/{accountId}/{bookingId}")
    public ResponseEntity<Booking> fetchBookingById(@PathVariable Integer accountId,@PathVariable Integer bookingId) {
        try {
            Booking fetchedBooking = bookingService.fetchById(accountId,bookingId);
            return ResponseEntity.status(HttpStatus.OK).body(fetchedBooking);
        } catch (Exception e) {
            throw new BookingNotFound("NO Booking Found");
        }
    }
    @PatchMapping("/update/{accountId}/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking,@PathVariable Integer accountId, @PathVariable Integer bookingId) {
        try {
            Booking updatedBooking = bookingService.fetchById(accountId, bookingId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
        } catch (Exception e) {
            throw new BookingNotFound("NO Booking Found");
        }
    }
}
