package com.revature.daos;

import com.revature.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends JpaRepository<Bookings,Integer> {
}
