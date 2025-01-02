package com.revature.controllers;

import com.revature.exceptions.hotel.HotelExistException;
import com.revature.exceptions.hotel.NotFoundHotelException;
import com.revature.models.Hotel;
import com.revature.models.Role;
import com.revature.services.HotelServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelServices hotelServices;

    @Autowired
    public HotelController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    }

    @PostMapping("/createHotel")
    public ResponseEntity<Hotel> createHotelHandler(@RequestBody Hotel hotel) throws HotelExistException {
        Hotel createdHotel = hotelServices.createHotel(hotel);
        return ResponseEntity.status(201).body(createdHotel);
    }

    @GetMapping("{hotelID}")
    public ResponseEntity<Hotel> getHotelByIdHandler(@PathVariable int roomID) throws NotFoundHotelException {
        Optional<Hotel> possibleHotel = Optional.ofNullable(hotelServices.findHotelByID(roomID));
        return possibleHotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    //TODO Get All Hotels
    @GetMapping()
    public ResponseEntity<Set<Hotel>> getAllHotelHandler(){
        return ResponseEntity.ok(hotelServices.getAllHotel());
    }

    // TODO Update Hotel Information
    @PutMapping("updateRoom/{hotelID}")
    public ResponseEntity<Hotel> updateHotelHandler(HttpSession session, @RequestBody Hotel hotel, @PathVariable int hotelID) throws NotFoundHotelException {
        if(session.isNew() || session.getAttribute("hotelName") == null){
            return ResponseEntity.status(401).build();
        }
        if(!Role.OWNER.equals(session.getAttribute("role"))){
            return ResponseEntity.status(401).build();
        }

        if(hotel.getHotelId() != hotelID){
            return ResponseEntity.status(400).build();
        }
        Hotel updateHotel = hotelServices.updateHotelInfo(hotel);
        if(updateHotel == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(updateHotel);
    }

    @DeleteMapping("/hotelDelete/{hotelId}")
    public ResponseEntity<Hotel> deleteHotelHandler(HttpSession session, @PathVariable int hotelId){
        if(session.isNew() || session.getAttribute("hotelName") == null){
            return ResponseEntity.status(401).build();
        }
        Hotel returnedHotel = hotelServices.removeHotel((String) session.getAttribute("hotelName"), hotelId);
        if(returnedHotel == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedHotel);
    }


    // TODO Search Hotels by Name or Filter Hotels by Amenities
}
