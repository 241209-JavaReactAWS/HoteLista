package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;
import com.revature.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /** Defining endpoints */

    /** Create new Account handler */
    @PostMapping
    public Account createNewAccountHandler(@RequestBody Account account){
        return accountService.createNewAccount(account);
    }

    /** Retrieving list of all Accounts handler */
    @GetMapping
    public List<Account> getAccountsHandler(){
        return accountService.getAllAccounts();
    }
}
