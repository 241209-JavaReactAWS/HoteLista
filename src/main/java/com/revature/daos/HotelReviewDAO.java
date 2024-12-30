package com.revature.daos;

import com.revature.models.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReviewDAO extends JpaRepository<HotelReview, Integer> {
}
