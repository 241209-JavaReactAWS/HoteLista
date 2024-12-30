package com.revature.controllers;

import com.revature.services.HotelReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelReviewController {
    private final HotelReviewService hotelReviewService;

    @Autowired
    public HotelReviewController(HotelReviewService hotelReviewService) {
        this.hotelReviewService = hotelReviewService;
    }
}