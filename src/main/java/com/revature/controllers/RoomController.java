package com.revature.controllers;

import com.revature.models.Room;
import com.revature.services.RoomServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomServices roomServices;

    @Autowired
    public RoomController(RoomServices roomServices) {
        this.roomServices = roomServices;
    }

    // TODO Create a Room
    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoomHandler(@RequestBody Room room){
       return ResponseEntity.status(200).body(roomServices.createRoom(room));
    }
    // TODO Get Room by ID

    // TODO Get All Rooms
    // TODO Update Room Information
    // TODO Delete Room


}
