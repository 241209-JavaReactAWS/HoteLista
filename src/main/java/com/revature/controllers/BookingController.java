package com.revature.controllers;

import com.revature.daos.BookingDAO;
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

    @PostMapping("/add/{accountId}/{roomId}")
    public ResponseEntity<BookingDTO> addNewBooking(@PathVariable Integer accountId, @PathVariable Integer roomId){
        try{
            BookingDTO newBooking = bookingService.addNewBooking(accountId, roomId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        }catch (Exception e) {
            throw new PaymentNotCreated("BOOKING FAILED");
        }
    }

    @GetMapping("/fetchAllBooking/{accountId}")
    public ResponseEntity<List<Booking>> fetchAlBooking(@PathVariable Integer accountId){
        try{
            List<Booking> bookingList = bookingService.fetchAllBookingList(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(bookingList);
        } catch (Exception e) {
            throw new PaymentNotFound("No LIST OF BOOKINGS FOUND");
        }
    }
    @GetMapping("/fetchById/{accountId}")
    public ResponseEntity<BookingDTO> fetchBookingById(@PathVariable Integer accountId) {
        try {
            BookingDTO fetchedBooking = bookingService.fetchById(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(fetchedBooking);
        } catch (Exception e) {
            throw new PaymentNotFound("NO Booking Found");
        }
    }
}
