package com.revature.controllers;

import com.revature.exceptions.hotel.NotFoundHotelException;
import com.revature.exceptions.hotelreview.NotFoundHotelReviewException;
import com.revature.models.HotelReview;
import com.revature.services.HotelReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel/{hotelId}/reviews")
public class HotelReviewController {
    private final HotelReviewService hotelReviewService;

    @Autowired
    public HotelReviewController(HotelReviewService hotelReviewService) {
        this.hotelReviewService = hotelReviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<HotelReview> addHotelReview(@PathVariable int hotelId, @RequestBody HotelReview hotelReview){
        try{
            HotelReview newHotelReview = hotelReviewService.addHotelReview(hotelId, hotelReview);
            return ResponseEntity.ok(newHotelReview);
        }catch (NotFoundHotelException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteHotelReview(@PathVariable int hotelId, @PathVariable int reviewId){
        hotelReviewService.deleteHotelReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<HotelReview> updateHotelReview(@PathVariable int hotelId, @PathVariable int reviewId, @RequestBody HotelReview hotelReview){
        try{
            HotelReview updatedHotelReview = hotelReviewService.updateHotelReview(hotelReview);
            return ResponseEntity.ok(updatedHotelReview);
        } catch (NotFoundHotelReviewException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
