package com.revature.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Entity
@Table(name = "inboxes")
@Getter
@Setter
@NoArgsConstructor
public class InboxItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inboxId;

    @OneToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @OneToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    private Date notificationTime;
    private String details;
}
