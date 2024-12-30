package com.revature.models;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_amenities")
public class HotelAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelAmenityId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Amenity amenity;

    public HotelAmenity() { }

    public int getHotelAmenityId() { return hotelAmenityId; }
    public void setHotelAmenityId(int hotelAmenityId) { this.hotelAmenityId = hotelAmenityId; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Amenity getAmenity() { return amenity; }
    public void setAmenity(Amenity amenity) { this.amenity = amenity; }
}
