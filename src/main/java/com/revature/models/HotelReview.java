package com.revature.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hotel_reviews")
public class HotelReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelReviewId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private double rating;
    private String content;
    private Date postedAt;

    public HotelReview() { }

    public int getHotelReviewId() { return hotelReviewId; }
    public void setHotelReviewId(int hotelReviewId) { this.hotelReviewId = hotelReviewId; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getPostedAt() { return postedAt; }
    public void setPostedAt(Date postedAt) { this.postedAt = postedAt; }
}
