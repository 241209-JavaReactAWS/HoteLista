package com.revature.daos;

import com.revature.models.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelAmenityDAO extends JpaRepository<HotelAmenity, Integer> {
}
