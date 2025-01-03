package com.revature.daos;

import com.revature.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelDAO extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByName(String name);

    Optional<Hotel> getByHotelName(String username);

    // Find hotels by amenities (assuming a Set of amenities)
    @Query("SELECT h FROM Hotel h JOIN h.amenities a WHERE a IN :amenities")
    List<Hotel> findByAmenities(@Param("amenities") Set<String> amenities);

    // Find hotels by both name and amenities
    @Query("SELECT h FROM Hotel h JOIN h.amenities a WHERE h.name LIKE %:name% AND a IN :amenities")
    List<Hotel> findByNameAndAmenities(@Param("name") String name, @Param("amenities") Set<String> amenities);
}
