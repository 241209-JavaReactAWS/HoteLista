package com.revature.controllers;

import com.revature.models.Role;
import com.revature.models.Room;
import com.revature.services.RoomServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
// TODO: Add CrossOrign with credetials and modify endpoints accordingly
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
    @GetMapping("{roomID}")
    public ResponseEntity<Room> getRoomByIdHandler(@PathVariable int roomID){
        Optional<Room> possibleRoom = Optional.ofNullable(roomServices.getRoomById(roomID));
        if(possibleRoom.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(possibleRoom.get());
    }

    // TODO Get All Rooms
    @GetMapping()
    public List<Room> getAllRoomsHandler(){
        return roomServices.getAllRooms();
    }
    // TODO Update Room Information

    @PutMapping("{roomID}")
    public ResponseEntity<Room> updateRoomHandler(HttpSession session, @RequestBody Room room, @PathVariable int roomID){
        // Check if session is new or user is not authenticated
        if(session.isNew() || session.getAttribute("roomName") == null) {
            return ResponseEntity.status(401).build();
        }
        // Check if the user is not the owner
        if (session.getAttribute("role") != Role.OWNER){
            return ResponseEntity.status(403).build();
        }
        // Check if room ID in body does not match the path variable
        if(room.getRoomId() != roomID){
            return ResponseEntity.status(400).build();
        }

        // Attempt to update the room
        Room updatedRoom = roomServices.updateRoom(room);

        if(updatedRoom == null){
            return ResponseEntity.status(404).build();
        }
        // Successfully updated the room
        return ResponseEntity.status(200).body(updatedRoom);
    }

    // TODO Delete Room
    //@DeleteMapping("/deleteRoom/{}")


}
