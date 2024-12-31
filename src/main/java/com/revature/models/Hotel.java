package com.revature.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelID;

    @Column(name = "hotel_name", nullable = false, unique = true)
    private String hotelName;

    @Column(name = "hotel_address", nullable = false)
    private String hotelAddress;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id") // This assumes a foreign key in the Amenity table
    private List<Amenity> hotelAmenities;

    private Set<Room> rooms;
    public Hotel() {
    }

    public Hotel(String hotelAddress, Set<Room> rooms, String hotelName, int hotelID, List<Amenity> hotelAmenities) {
        this.hotelAddress = hotelAddress;
        this.rooms = rooms;
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.hotelAmenities = hotelAmenities;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public List<Amenity> getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(List<Amenity> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}