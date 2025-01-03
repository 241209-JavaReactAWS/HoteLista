package com.revature.requests;

import com.revature.models.*;
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
    private BookingStatus status;
    private Double totalPrice;
    private Integer lengthOfStay;
    private RoomsType type;
    private String cardHolderName;
    private String cardNumber;

}
