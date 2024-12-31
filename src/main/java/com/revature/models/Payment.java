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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    private String cardHolderName;

    //TODO: Validation For CardNumber Format
    @Column(unique = true)
    private String cardNumber;
    @Column(name = "cvv")
//    @NotNull
//    @Size(min=3, max=4)
    private int cvv;
    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
