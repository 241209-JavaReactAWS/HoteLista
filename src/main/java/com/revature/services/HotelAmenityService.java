package com.revature.services;

import com.revature.daos.HotelAmenityDAO;
import com.revature.exceptions.hotelamenity.ExistsHotelAmenityException;
import com.revature.models.Amenity;
import com.revature.models.Hotel;
import com.revature.models.HotelAmenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class HotelAmenityService {
    private final HotelAmenityDAO hotelAmenityDAO;
    private final HotelServices hotelServices;

    @Autowired
    public HotelAmenityService(HotelAmenityDAO hotelAmenityDAO, HotelServices hotelServices) {
        this.hotelAmenityDAO = hotelAmenityDAO;
        this.hotelServices = hotelServices;
    }

    public HotelAmenity addHotelAmenity(HotelAmenity hotelAmenity) throws ExistsHotelAmenityException {
        Optional<HotelAmenity> optionalHotelAmenity = hotelAmenityDAO.findById(hotelAmenity.getHotelAmenityId());

        if(optionalHotelAmenity.isPresent()){
            throw new ExistsHotelAmenityException("");
        }
        return hotelAmenityDAO.save(hotelAmenity);
    }

    public void deleteHotelAmenity(int hotelAmenityId){
        hotelAmenityDAO.deleteById(hotelAmenityId);
    }

    public Set<HotelAmenity> getAllHotelAmenity(int hotelId){
        return hotelAmenityDAO.findAllByHotel_HotelId(hotelId);
    }

    public Set<Hotel> getAllHotelByAmenity(Set<Amenity> amenities){
        Set<Hotel> hotels = hotelServices.getAllHotel();

        for(Amenity amenity : amenities){
            hotels.retainAll(hotelAmenityDAO.findHotelsByAmenity(amenity));
        }

        return hotels;
    }
}
