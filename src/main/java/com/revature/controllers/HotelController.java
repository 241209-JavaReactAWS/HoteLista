package com.revature.controllers;

import com.revature.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
// TODO: Add CrossOrign with credetials and modify endpoints accordingly
public class HotelController {
    private final HotelServices hotelServices;

    @Autowired
    public HotelController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    }

    // TODO Create Hotel
    // TODO Get Hotel by ID
    // TODO Get All Hotels
    // TODO Update Hotel Information
    // TODO Delete Hotel
    // TODO Search Hotels by Name
    // TODO Filter Hotels by Amenities
}
