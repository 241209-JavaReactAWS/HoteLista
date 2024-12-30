package com.revature.services;

import com.revature.daos.HotelDAO;
import com.revature.models.Hotel;
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

    @Autowired
    public HotelServices(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    // TODO Create Hotel
    public Hotel createHotel(Hotel hotel){
        return hotelDAO.save(hotel);
    }
    // TODO Get Hotel by ID
    public Optional<Hotel> findHotelByID(int id){
        return hotelDAO.findById(id);
    }

    // TODO Get All Hotels
    public Set<Hotel> getAllHotel(){
        List<Hotel> potetionalHotels = hotelDAO.findAll();;
        return new HashSet<Hotel>(potetionalHotels);
    }
    // TODO Update Hotel Information
    public Hotel updateHotelInfo(Hotel hotelToBeUpdated){
        return hotelDAO.save(hotelToBeUpdated);
    }
    // TODO Delete Hotel
    public void deleteHotel(Hotel hotel){
        hotelDAO.delete(hotel);
    }
    // TODO Search Hotels by Name
    public Optional<Hotel> getHotelByName(String hotelName){
        Optional<Hotel> possibleHotel = hotelDAO.getByHotelName(hotelName);
        return possibleHotel;
    }
    // TODO Filter Hotels by Amenities
    public Set<Hotel> filterHotelsByAmenities(Set<String> amenities){
        Set<Hotel> allHotels = getAllHotel();
        if(amenities.isEmpty()){
            return allHotels;
        }
        Set<Hotel> filteredHotels = allHotels.stream()
                .filter(hotel -> hotel.getHotelAmenities() != null
                        && hotel.getHotelAmenities().contains(amenities )).collect(Collectors.toSet());
        return  filteredHotels;
//
    }

}
