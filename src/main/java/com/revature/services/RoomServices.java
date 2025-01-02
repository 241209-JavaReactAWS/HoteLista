package com.revature.services;

import com.revature.daos.HotelDAO;
import com.revature.daos.RoomDAO;
import com.revature.exceptions.room.NotFoundRoomException;
import com.revature.models.Hotel;
import com.revature.models.Room;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServices {
    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;

    public RoomServices(RoomDAO roomDAO, HotelDAO hotelDAO) {
        this.roomDAO = roomDAO;
        this.hotelDAO = hotelDAO;
    }

    public Room createRoom(Room room) {
        if (roomDAO.findById(room.getRoomId()).isPresent()) {
            throw new NotFoundRoomException("Room already exist");
        }
        return roomDAO.save(room);
    }

    public Room getRoomById(int id) throws NotFoundRoomException {
        Optional<Room> optionalRoom = roomDAO.findById(id);
        if (optionalRoom.isEmpty()) {
            throw new NotFoundRoomException("");
        }
        return optionalRoom.get();
    }

    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }

    public Room updateRoom(Room room) {
        Optional<Room> possibleRoom = roomDAO.findById(room.getRoomId());
        if (possibleRoom.isEmpty()) {
            throw new NotFoundRoomException("Room Does not Exist!");
        }
        Room actualRoom = possibleRoom.get();
        actualRoom.setGuestCapacity(room.getGuestCapacity());
        actualRoom.setType(room.getType());
        actualRoom.setAvailable(room.getAvailable());
        return roomDAO.save(actualRoom);
    }

    public Hotel removeRoomFromHotel(String hotelName, int roomID) {
        // Look the Hotel
        Optional<Hotel> possibleHotel = hotelDAO.getByHotelName(hotelName);

        // Look up the Room
        Optional<Room> possibleRoom = roomDAO.findById(roomID);

        // Validate that they both exist
        if (possibleHotel.isEmpty() || possibleRoom.isEmpty()) {
            return null;
        }

        // values that exist
        Hotel returnedHotel = possibleHotel.get();
        Room returnedRoom = possibleRoom.get();

        // Remove the room from the hotel list
        Set<Room> roomsRemoved = returnedHotel.getRooms();
        roomsRemoved.remove(returnedRoom);
        returnedHotel.setRooms(roomsRemoved);

        // Explicitly delete the room from the database
        roomDAO.delete(returnedRoom);
        // save the hotel now the room is removed
        return hotelDAO.save(returnedHotel);
    }

}
