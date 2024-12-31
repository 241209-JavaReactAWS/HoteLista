package com.revature.services;

import com.revature.daos.RoomDAO;
import com.revature.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServices {
    private final RoomDAO roomDAO;

    public RoomServices(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    //TODO Create a Room
    public Room createRoom(Room room){
        return roomDAO.save(room);
    }
    //TODO Get Room by ID
    public Optional<Room> getRoomById(int id){
        return roomDAO.findById(id);
    }
    //TODO Get All Rooms
    public List<Room> getAllRooms(){
        return roomDAO.findAll();
    }
    //TODO Update Room Information
    public Room updateRoom(Room updatedRoom){
        return roomDAO.save(updatedRoom);
    }
    //TODO Delete Room
    public void deleteRoom(Room room){
       roomDAO.delete(room);
    }

   
}
