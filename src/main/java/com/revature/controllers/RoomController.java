package com.revature.controllers;

import com.revature.models.Hotel;
import com.revature.models.Role;
import com.revature.models.Room;
import com.revature.services.HotelServices;
import com.revature.services.RoomServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")

public class RoomController {
    private final RoomServices roomServices;

    @Autowired
    public RoomController(RoomServices roomServices, HotelServices hotelServices) {
        this.roomServices = roomServices;

    }

    // TODO Create a Room
    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoomHandler(@RequestBody Room room) {
        Room roomCreated = roomServices.createRoom(room);
        return ResponseEntity.status(201).body(roomCreated);
    }

    // TODO Get Room by ID
    @GetMapping("{roomID}")
    public ResponseEntity<Room> getRoomByIdHandler(@PathVariable int roomID) {
        Optional<Room> possibleRoom = Optional.ofNullable(roomServices.getRoomById(roomID));
        return possibleRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    // TODO Get All Rooms
    @GetMapping()
    public ResponseEntity<List<Room>> getAllRoomsHandler() {
        return ResponseEntity.ok(roomServices.getAllRooms());
    }
    // TODO Update Room Information

    @PutMapping("{roomID}")
    public ResponseEntity<Room> updateRoomHandler(HttpSession session, @RequestBody Room room,
            @PathVariable int roomID) {
        // Check if session is new or user is not authenticated
        if (session.isNew() || session.getAttribute("roomName") == null) {
            return ResponseEntity.status(401).build();
        }
        // Check if the user is not the owner
        if (!Role.OWNER.equals(session.getAttribute("role"))) {
            return ResponseEntity.status(403).build();
        }
        // Check if room ID in body does not match the path variable
        if (room.getRoomId() != roomID) {
            return ResponseEntity.status(400).build();
        }

        // Attempt to update the room
        Room updatedRoom = roomServices.updateRoom(room);

        if (updatedRoom == null) {
            return ResponseEntity.status(404).build();
        }
        // Successfully updated the room
        return ResponseEntity.status(200).body(updatedRoom);
    }


    @DeleteMapping("/roomDelete/{roomId}")
    public  ResponseEntity<Hotel> deleteRoomFromHotelHandler(HttpSession session, @PathVariable int roomId){
        if(session.isNew() || session.getAttribute("hotelName") == null){
            return ResponseEntity.status(401).build();
        }
        Hotel returnedHotel = roomServices.removeRoomFromHotel((String) session.getAttribute("hotelName"), roomId);

        if(returnedHotel == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedHotel);
    }

}
