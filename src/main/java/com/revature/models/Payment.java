package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Column(nullable = false)
    private String cardHolderName;
    @Column(unique = true, nullable = false)
    @CreditCardNumber
    private String cardNumber;
    @Column(nullable = false)
    private Integer cvv;
    @Column(nullable = false)
    private String postalCode;
//    default method

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "payment")
    @JsonIgnoreProperties("payment")
    private List<Booking> bookingsList;

}
