package com.revature.daos;

import com.revature.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelDAO extends JpaRepository<Hotel, Integer> {

    Optional<Hotel> getByHotelName(String username);
}
