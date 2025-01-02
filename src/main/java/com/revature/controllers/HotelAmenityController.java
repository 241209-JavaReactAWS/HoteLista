package com.revature.controllers;

import com.revature.exceptions.hotelamenity.ExistsHotelAmenityException;
import com.revature.models.Hotel;
import com.revature.models.HotelAmenity;
import com.revature.services.HotelAmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/hotel/{hotelId}/amenities")
public class HotelAmenityController {
    private final HotelAmenityService hotelAmenityService;

    @Autowired
    public HotelAmenityController(HotelAmenityService hotelAmenityService) {
        this.hotelAmenityService = hotelAmenityService;
    }

    @PostMapping("/add")
    public ResponseEntity<HotelAmenity> addHotelAmenity(@PathVariable int hotelId,@RequestBody HotelAmenity hotelAmenity){
        try{
            HotelAmenity newHotelAmenity = hotelAmenityService.addHotelAmenity(hotelAmenity);
            return ResponseEntity.ok(newHotelAmenity);
        }catch(ExistsHotelAmenityException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{hotelAmenityId}")
    public ResponseEntity<Void> deleteHotelAmenity(@PathVariable int hotelId, @PathVariable int hotelAmenityId){
        hotelAmenityService.deleteHotelAmenity(hotelAmenityId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Set<HotelAmenity>> getAllByHotelId(@PathVariable int hotelId){
        Set<HotelAmenity> hotelAmenities = hotelAmenityService.getAllHotelAmenity(hotelId);
        return ResponseEntity.ok(hotelAmenities);
    }
}
