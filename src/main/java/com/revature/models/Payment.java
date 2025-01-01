package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private String cardHolderName;
    //TODO: Validation For CardNumber Format
//    @Column(unique = true)
    private String cardNumber;
    private int cvv;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
