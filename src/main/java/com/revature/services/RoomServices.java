package com.revature.services;

import com.revature.daos.RoomDAO;
import com.revature.exceptions.room.NotFoundRoomException;
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

    public Room createRoom(Room room) {
        // TODO: Check if room exists (if it exists it should be an update instead)
        return roomDAO.save(room);
    }

    public Room getRoomById(int id) throws NotFoundRoomException {
        Optional<Room> optionalRoom = roomDAO.findById(id);
        if(optionalRoom.isEmpty()){
            throw new NotFoundRoomException("");
        }
        return optionalRoom.get();
    }

    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }

    public Room updateRoom(Room room) {
        Optional<Room> possibleRoom = roomDAO.findById(room.getRoomId());
        if(possibleRoom.isEmpty()){
            throw new NotFoundRoomException("");
        }
        Room actualRoom = possibleRoom.get();
        actualRoom.setGuestCapacity(room.getGuestCapacity());
        actualRoom.setType(room.getType());
        actualRoom.setAvailable(room.getAvailable());
        return roomDAO.save(actualRoom);
    }
}
