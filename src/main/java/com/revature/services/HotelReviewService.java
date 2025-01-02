package com.revature.services;

import com.revature.daos.HotelReviewDAO;
import com.revature.exceptions.hotel.NotFoundHotelException;
import com.revature.exceptions.hotelreview.NotFoundHotelReviewException;
import com.revature.models.HotelReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelReviewService {
    private final HotelReviewDAO hotelReviewDAO;
    private final HotelServices hotelServices;

    @Autowired
    public HotelReviewService(HotelReviewDAO hotelReviewDAO, HotelServices hotelServices) {
        this.hotelReviewDAO = hotelReviewDAO;
        this.hotelServices = hotelServices;
    }

    public HotelReview addHotelReview(int hotelId, HotelReview hotelReview) throws NotFoundHotelException {
        hotelServices.findHotelByID(hotelId);
        return hotelReviewDAO.save(hotelReview);
    }

    public void deleteHotelReview(int hotelReviewId){
        hotelReviewDAO.deleteById(hotelReviewId);
    }

    public HotelReview updateHotelReview(HotelReview hotelReview) throws NotFoundHotelReviewException{
        Optional<HotelReview> optionalHotelReview = hotelReviewDAO.findById(hotelReview.getHotelReviewId());

        if(optionalHotelReview.isEmpty()) {
            throw new NotFoundHotelReviewException();
        }

        return hotelReviewDAO.save(hotelReview);
    }

}
