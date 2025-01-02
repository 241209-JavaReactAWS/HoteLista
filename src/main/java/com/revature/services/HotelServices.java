package com.revature.services;

import com.revature.daos.HotelDAO;
import com.revature.daos.RoomDAO;
import com.revature.exceptions.hotel.HotelExistException;
import com.revature.exceptions.hotel.NotFoundHotelException;
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
    private final RoomServices roomServices;

    @Autowired
    public HotelServices(HotelDAO hotelDAO, RoomServices roomServices) {
        this.hotelDAO = hotelDAO;
        this.roomServices = roomServices;
    }

    public Hotel createHotel(Hotel hotel) throws HotelExistException {
        //TODO: Check hotel exists
        if(hotelDAO.findById(hotel.getHotelId()).isPresent()){
            throw new HotelExistException("Hotel Already Exist!");
        }
        return hotelDAO.save(hotel);
    }

    public Hotel findHotelByID(int id) throws NotFoundHotelException {
        Optional<Hotel> optionalHotel =  hotelDAO.findById(id);
        if(optionalHotel.isEmpty()){
            throw new NotFoundHotelException("");
        }
        return optionalHotel.get();
    }

    public Set<Hotel> getAllHotel() {
        List<Hotel> potentialHotels = hotelDAO.findAll();
        return new HashSet<Hotel>(potentialHotels);
    }


    public Hotel updateHotelInfo(Hotel hotelToBeUpdated) throws NotFoundHotelException {

        if(hotelDAO.findById(hotelToBeUpdated.getHotelId()).isPresent()){
            throw new NotFoundHotelException("Hotel Already Exist");
        }
        return hotelDAO.save(hotelToBeUpdated);
    }

    public void deleteHotel(Hotel hotel) {
        hotelDAO.delete(hotel);
    }

    public Hotel getHotelByName(String hotelName) throws NotFoundHotelException {
        Optional<Hotel> optionalHotel =  hotelDAO.getByHotelName(hotelName);
        if(optionalHotel.isEmpty()){
            throw new NotFoundHotelException("");
        }
        return optionalHotel.get();
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
}
