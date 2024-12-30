package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    private String cardHolderName;
    private Integer cardNumber;
    private Integer cvv;
    private Integer postalcode;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserAccount user;

}
