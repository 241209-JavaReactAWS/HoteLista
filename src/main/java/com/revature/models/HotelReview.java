package com.revature.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "hotel_reviews")
@Setter
@Getter
@NoArgsConstructor
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

    // TODO: Ensure its only to the tenth place
    private double rating;
    private String content;
    private Date postedAt;

    // TODO: Add Edited Status

}
