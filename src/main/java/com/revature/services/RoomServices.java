package com.revature.services;

import com.revature.daos.HotelDAO;
import com.revature.daos.RoomDAO;
import com.revature.models.Hotel;
import com.revature.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServices {
    private final RoomDAO roomDAO;


    public RoomServices(RoomDAO roomDAO, HotelDAO hotelDAO) {
        this.roomDAO = roomDAO;

    }

    // TODO Create a Room
    public Room createRoom(Room room) {
        return roomDAO.save(room);
    }

    // TODO Get Room by ID
    public Optional<Room> getRoomById(int id) {
        return roomDAO.findById(id);
    }

    // TODO Get All Rooms
    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }

    // TODO Update Room Information
    public Room updateRoom(Room room) {
        Optional<Room> possibleRoom = roomDAO.findById(room.getRoomId());

        if(possibleRoom.isPresent()){
            Room actualRoom = possibleRoom.get();

            actualRoom.setRoomAmenties(room.getRoomAmenties());
            actualRoom.setGuestCapacity(room.getGuestCapacity());
            actualRoom.setType(room.getType());

            return roomDAO.save(actualRoom);
        }
        return null;
    }




}
