package com.revature.requests;

import com.revature.models.Account;
import com.revature.models.Payment;
import com.revature.models.Room;
import com.revature.models.RoomsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private Integer bookingId;
    private RoomsType type;
    private int guestCapacity;
    private int available;
//    private Payment payment;


//    private Integer lengthOfStay;
//    private Date checkInDate;
//    private Date checkOutDate;
}
