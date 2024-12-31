package com.revature.services;

import com.revature.daos.HotelDAO;
import com.revature.daos.RoomDAO;
import com.revature.models.Hotel;
import com.revature.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelServices {

    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;

    @Autowired
    public HotelServices(HotelDAO hotelDAO, RoomDAO roomDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
    }

    // TODO Create Hotel
    public Hotel createHotel(Hotel hotel) {
        return hotelDAO.save(hotel);
    }

    // TODO Get Hotel by ID
    public Optional<Hotel> findHotelByID(int id) {
        return hotelDAO.findById(id);
    }

    // TODO Get All Hotels
    public Set<Hotel> getAllHotel() {
        List<Hotel> potentialHotels = hotelDAO.findAll();
        return new HashSet<Hotel>(potentialHotels);
    }

    // TODO Update Hotel Information
    public Hotel updateHotelInfo(Hotel hotelToBeUpdated) {
        return hotelDAO.save(hotelToBeUpdated);
    }

    // TODO Delete Hotel
    public void deleteHotel(Hotel hotel) {
        hotelDAO.delete(hotel);
    }

    // TODO Search Hotels by Name
    public Optional<Hotel> getHotelByName(String hotelName) {
        return hotelDAO.getByHotelName(hotelName);
    }

    // TODO Filter Hotels by Amenities
    public Set<Hotel> filterHotelsByAmenities(Set<String> amenities) {
        Set<Hotel> allHotels = getAllHotel();
        if (amenities.isEmpty()) {
            return allHotels;
        }
        return allHotels.stream()
                .filter(hotel -> hotel.getHotelAmenities() != null
                        && hotel.getHotelAmenities().contains(amenities))
                .collect(Collectors.toSet());

    }
    // TODO add Room to hotel
    public Hotel addRoomFromHotel(String hotelname, int roomID) {
        Optional<Hotel> possibleHotel = hotelDAO.getByHotelName(hotelname);
        Optional<Room> possibleRoom = roomDAO.findById(roomID);

        if(possibleHotel.isEmpty() || possibleRoom.isEmpty()){
            return null;
        }
        Hotel actualHotel = possibleHotel.get();
        Room actualRoom = possibleRoom.get();
        if(!actualHotel.getRooms().contains(actualRoom)){
            return null;
        }
        Set<Room> rooms = actualHotel.getRooms();
        rooms.add(actualRoom);
        actualHotel.setRooms(rooms);

        return hotelDAO.save(actualHotel);
    }

    // TODO Delete Room from hotel
    public Hotel removeRoomFromHotel(String hotelname, int roomID) {
        Optional<Hotel> possibleHotel = hotelDAO.getByHotelName(hotelname);
        Optional<Room> possibleRoom = roomDAO.findById(roomID);

        if(possibleHotel.isEmpty() || possibleRoom.isEmpty()){
            return null;
        }
        Hotel actualHotel = possibleHotel.get();
        Room actualRoom = possibleRoom.get();
        if(!actualHotel.getRooms().contains(actualRoom)){
            return null;
        }
        Set<Room> rooms = actualHotel.getRooms();
        rooms.remove(actualRoom);
        actualHotel.setRooms(rooms);

        return hotelDAO.save(actualHotel);
    }

}
