package com.revature.services;

import com.revature.daos.HotelReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelReviewService {
    private final HotelReviewDAO hotelReviewDao;

    @Autowired
    public HotelReviewService(HotelReviewDAO hotelReviewDao) {
        this.hotelReviewDao = hotelReviewDao;
    }


}
