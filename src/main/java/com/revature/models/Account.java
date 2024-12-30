package com.revature.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isOwner;

    public Account() {

    }
    
    public Account(int accountId, String email, String password, boolean isOwner) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.isOwner = isOwner;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRole() {
        return isOwner;
    }

    public void setRole(boolean isOwner) {
        this.isOwner = isOwner;
    }
}
