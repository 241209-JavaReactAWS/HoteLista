package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.models.Account;

@Service
public class AccountService {
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /** Create a new Account */
    public Account createNewAccount(Account account){
        return accountDAO.save(account);
    }

    /** Get a list of all Accounts */
    public List<Account> getAllAccounts(){
        return accountDAO.findAll();
    }

    /** Look up (Filter/Search) Accounts by accountId */
    public Account searchById(int accountId){
        Optional<Account> acc = accountDAO.findById(accountId);
        return acc.orElse(null);
    }

    /** Look up (Filter/Search) Accounts by email */
    public Account searchByEmail(String email){
        Optional<Account> acc = accountDAO.findByEmail(email);
        return acc.orElse(null);
    }

    /** Look up (Filter/Search) Accounts by role type: user or hotel owner */
    public List<Account> searchByRole(boolean isOwner){
        return accountDAO.findByRole(isOwner);
    }
}
