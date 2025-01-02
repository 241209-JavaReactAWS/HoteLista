package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

//    @ManyToOne
//    @JoinColumn(name = "room_id")
//    private Room room;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private Double totalPrice;
    private Integer lengthOfStay;
    private Date checkInDate;
    private Date checkOutDate;
//    private Payment payment;

//    @OneToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;

//    add room availability updates automatically after checkout
}
