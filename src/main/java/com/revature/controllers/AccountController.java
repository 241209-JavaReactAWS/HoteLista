package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;
import com.revature.models.Hotel;
import com.revature.services.AccountService;
import com.revature.services.HotelServices;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/accounts")
// TODO: Add CrossOrign with credetials and modify endpoints accordingly
public class AccountController {
    private final AccountService accountService;
    private final HotelServices hotelService;

    @Autowired
    public AccountController(AccountService accountService, HotelServices hotelService) {
        this.accountService = accountService;
        this.hotelService = hotelService;
    }

    /** Defining endpoints */

    /** Create new Account handler */
    @PostMapping("/register")
    public ResponseEntity<Account> registerNewAccountHandler(@RequestBody Account account, @RequestBody Hotel hotel, HttpSession session){
        // TODO: UPDATED session here
        try {
            Account acc;
            if (account.getRole()) {
                /* Hotel Owner */
                session.setAttribute("role", "HotelOwner");
                acc = accountService.registerNewAccount(account, hotel);

            } else {
                /* Normal User Account */
                session.setAttribute("role", "NormalUser");
                acc = accountService.registerNewAccount(account, null);
            }
            return ResponseEntity.ok(acc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /** Login Account handler */
    @PostMapping("/users/login")
    public ResponseEntity<Account> loginAccountHandler(@RequestBody Account account, HttpSession session){
        try {
            Account acc = accountService.loginAccount(account);
            session.setAttribute("accountId", acc.getAccountId());
            session.setAttribute("email", acc.getEmail());
            session.setAttribute("isOwner", acc.getRole());
            return ResponseEntity.ok(acc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /** Retrieving a list of all Accounts handler */
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccountsHandler(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
