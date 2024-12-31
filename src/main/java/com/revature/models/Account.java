package com.revature.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Column(name = "role")
    private boolean isOwner;

    @OneToMany(mappedBy = "payment_id")
    private List<Payment> paymentsList;

    public boolean getRole(){ return isOwner; }
    public void setRole(boolean isOwner){ this.isOwner = isOwner; }

}
