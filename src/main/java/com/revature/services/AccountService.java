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

    // TODO: Create specific exceptions
    /** Register a new User Account 
         * @throws Exception */
        public Account registerNewAccount(Account account) throws Exception {
        Optional<Account> acc = accountDAO.findById(account.getAccountId());
        if (acc.isPresent()) {
            throw new Exception("This account already exists. Please register with new information");
        }
        // TODO: ONEtoONE between owner and HOTEL

        return accountDAO.save(account);
    }

    /** Login a User Account 
         * @throws Exception */
        public Account loginAccount(Account account) throws Exception {
        Optional<Account> acc = accountDAO.findByEmailAndPassword(account.getEmail(), account.getPassword());
        if (acc.isEmpty()) {
            throw new Exception("Account not found. Please enter valid credentials.");
        }
        return acc.get();
    }

    /** Get a list of all Accounts */
    public List<Account> getAllAccounts(){
        return accountDAO.findAll();
    }

    /** Look up (Filter/Search) Accounts by accountId 
         * @throws Exception */
        public Account searchById(int accountId) throws Exception {
        Optional<Account> acc = accountDAO.findById(accountId);
        if (acc.isEmpty()) {
            throw new Exception("User with this ID not found");
        }
        return acc.get();
    }

    /** Look up (Filter/Search) Accounts by email 
         * @throws Exception */
        public Account searchByEmail(String email) throws Exception {
        Optional<Account> acc = accountDAO.findByEmail(email);
        if (acc.isEmpty()) {
            throw new Exception("User with this Email Address not found");
        }
        return acc.get();
    }

    /** Look up (Filter/Search) Accounts by role type: user or hotel owner */
    public List<Account> searchByRole(boolean isOwner) {
        return accountDAO.findByRole(isOwner);
    }

    /** Delete Account by accountId 
         * @throws Exception */
    public void deleteById(int accountId) throws Exception {
        accountDAO.deleteById(accountId);
    }

    /** Edit a User Account 
         * @throws Exception */
        public Account editAccount(Account account) throws Exception {
            Optional<Account> acc = accountDAO.findById(account.getAccountId());
            if (acc.isEmpty()) {
                throw new Exception("Account not found. Please try again");
            }
            return accountDAO.save(account);
        }
}
