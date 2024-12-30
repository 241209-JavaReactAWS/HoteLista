package com.revature.services;

import com.revature.daos.HotelReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelReviewService {
    private final HotelReviewDao hotelReviewDao;

    @Autowired
    public HotelReviewService(HotelReviewDao hotelReviewDao) {
        this.hotelReviewDao = hotelReviewDao;
    }

}
