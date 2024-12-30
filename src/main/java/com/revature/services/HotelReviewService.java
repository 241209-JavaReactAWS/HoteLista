package com.revature.services;

import com.revature.daos.HotelReviewDAO;
import com.revature.exceptions.hotelreview.InvalidContentHotelReviewException;
import com.revature.exceptions.hotelreview.NotFoundHotelReviewException;
import com.revature.models.HotelReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelReviewService {
    private final HotelReviewDAO hotelReviewDao;

    @Autowired
    public HotelReviewService(HotelReviewDAO hotelReviewDao) {
        this.hotelReviewDao = hotelReviewDao;
    }

    public HotelReview addHotelReview(HotelReview hotelReview){
        return hotelReviewDao.save(hotelReview);
    }

    public void deleteHotelReview(int hotelReviewId){
        hotelReviewDao.deleteById(hotelReviewId);
    }

    public HotelReview updateHotelReview(HotelReview hotelReview) throws NotFoundHotelReviewException{
        Optional<HotelReview> optionalHotelReview = hotelReviewDao.findById(hotelReview.getHotelReviewId());

        if(optionalHotelReview.isEmpty()) {
            throw new NotFoundHotelReviewException();
        }

        return hotelReviewDao.save(hotelReview);
    }
}
