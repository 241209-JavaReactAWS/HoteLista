package com.revature.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Enumerated(EnumType.STRING)
    private RoomsType type;

    @Column(name = "guest_capacity")
    private int guestCapacity;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Amenties> roomAmenties;

    public Room() {
    }

    public Room(int guestCapacity, RoomsType type, int roomId, List<Amenties> roomAmenties) {
        this.guestCapacity = guestCapacity;
        this.type = type;
        this.roomId = roomId;
        this.roomAmenties = roomAmenties;
    }

    public int getGuestCapacity() {
        return guestCapacity;
    }

    public void setGuestCapacity(int guestCapacity) {
        this.guestCapacity = guestCapacity;
    }

    public RoomsType getType() {
        return type;
    }

    public void setType(RoomsType type) {
        this.type = type;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<Amenties> getRoomAmenties() {
        return roomAmenties;
    }

    public void setRoomAmenties(List<Amenties> roomAmenties) {
        this.roomAmenties = roomAmenties;
    }
}



