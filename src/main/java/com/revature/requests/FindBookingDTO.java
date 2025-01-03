package com.revature.requests;

import com.revature.models.BookingStatus;
import com.revature.models.RoomsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindBookingDTO {

    private Integer bookingId;
    private BookingStatus status;
    private Double totalPrice;
    private Integer lengthOfStay;
    private Integer paymentId;
}
