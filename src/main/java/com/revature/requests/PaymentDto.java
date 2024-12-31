package com.revature.requests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

        private Integer paymentId;
        @Column(name = "card_holder_name")
        private String cardHolderName;
        @Column(name = "card_number", unique = true)
        @CreditCardNumber
        private Integer cardNumber;
        @Column(name = "cvv")
        @NotNull
        @Size(min=3, max=4)
        private Integer cvv;
        @Column(name = "postal_code")
        private Integer postalcode;

    }
