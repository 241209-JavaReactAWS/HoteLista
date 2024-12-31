package com.revature.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;


@Table(name = "hotels")
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false, unique = true)
    private String hotelName;

    @Column(nullable = false)
    private String hotelAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_amenity_id")
    private Set<HotelAmenity> hotelAmenities;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Set<Room> rooms;
}