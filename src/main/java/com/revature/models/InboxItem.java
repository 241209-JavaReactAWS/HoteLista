package com.revature.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inboxes")
public class InboxItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inboxId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private Date notificationTime;
    private String details;

    public InboxItem() { }

    public int getInboxId() { return inboxId; }
    public void setInboxId(int inboxId) { this.inboxId = inboxId; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Date getNotificationTime() { return notificationTime; }
    public void setNotificationTime(Date notificationTime) { this.notificationTime = notificationTime; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
