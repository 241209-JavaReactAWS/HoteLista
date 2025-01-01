package com.revature.requests;


import com.revature.models.Account;
import com.revature.models.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
        private Integer paymentId;
        private String cardHolderName;
        private String cardNumber;
        private Integer cvv;
        private String postalCode;
}
