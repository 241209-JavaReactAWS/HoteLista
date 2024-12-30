package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingsId;
    private Integer userId;
    private Integer hotelId;
    private Date checkInDate;
    private Date checkOutDate;

//    @ManyToOne
//   @JoinColumn(name = "user_id")
//   private User user_booking;

}
