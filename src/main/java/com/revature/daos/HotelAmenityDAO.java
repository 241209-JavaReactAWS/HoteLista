package com.revature.daos;

import com.revature.models.Amenity;
import com.revature.models.Hotel;
import com.revature.models.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HotelAmenityDAO extends JpaRepository<HotelAmenity, Integer> {
    Set<HotelAmenity> findAllByHotel_HotelId(int hotelId);

    @Query("SELECT ha.hotel FROM HotelAmenity ha WHERE ha.amenity = :amenity")
    Set<Hotel> findHotelsByAmenity(@Param("amenity") Amenity amenity);
}
