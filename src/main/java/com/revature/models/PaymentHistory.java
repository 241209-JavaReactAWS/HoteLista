package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentHistoryId;
    private Integer userId;
    private Integer hotelId;
    private Double cost;
    private Date dateOfPurchase;
    private Boolean isCanceled;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserAccount user;


}
