package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.daos.HotelDAO;
import com.revature.models.Account;
import com.revature.models.Hotel;

@Service
public class AccountService {
    private final AccountDAO accountDAO;
    private final HotelDAO hotelDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO, HotelDAO hotelDAO) {
        this.accountDAO = accountDAO;
        this.hotelDAO = hotelDAO;
    }

    // TODO: Create specific exceptions
    /** Register a new User Account 
         * @throws DuplicateKeyException */
    public Account registerNewAccount(Account account, Hotel hotel) throws DuplicateKeyException {
        Optional<Account> acc = accountDAO.findById(account.getAccountId());
        if (acc.isPresent()) {
            throw new DuplicateKeyException("This account already exists. Please register with new information");
        }
        /** boolean isOwner evaluates to true, meaning a hotel owner is trying to register */
        if (acc.get().getRole()) {
            /** Register the hotel owner and save the new hotel to hotel table, modify if hotel already exists */
            hotel.setAccount(account);
            hotelDAO.save(hotel);
        }
        // TODO: ONEtoONE between owner and HOTEL
        return accountDAO.save(account);
    }

    /** Login a User Account 
         * @throws NullPointerException */
    public Account loginAccount(Account account) throws NullPointerException {
        Optional<Account> acc = accountDAO.findByEmailAndPassword(account.getEmail(), account.getPassword());
        if (acc.isEmpty()) {
            throw new NullPointerException("Account not found. Please enter valid credentials.");
        }
        return acc.get();
    }

    /** Get a list of all Accounts */
    public List<Account> getAllAccounts(){
        return accountDAO.findAll();
    }

    /** Look up (Filter/Search) Accounts by accountId 
         * @throws NullPointerException */
    public Account searchById(int accountId) throws NullPointerException {
        Optional<Account> acc = accountDAO.findById(accountId);
        if (acc.isEmpty()) {
            throw new NullPointerException("User with this ID not found");
        }
        return acc.get();
    }

    /** Look up (Filter/Search) Accounts by email 
         * @throws NullPointerException */
    public Account searchByEmail(String email) throws NullPointerException {
        Optional<Account> acc = accountDAO.findByEmail(email);
        if (acc.isEmpty()) {
            throw new NullPointerException("User with this Email Address not found");
        }
        return acc.get();
    }

    /** Look up (Filter/Search) Accounts by role type: user or hotel owner */
    public List<Account> searchByRole(boolean isOwner) {
        return accountDAO.findByisOwner(isOwner);
    }

    /** Delete Account by accountId 
         * @throws IllegalArgumentException */
    public void deleteById(int accountId) throws IllegalArgumentException {
        if (accountId < 0)
            throw new IllegalArgumentException("Please enter a valid account ID.");
        accountDAO.deleteById(accountId);
    }

    /** Edit a User Account 
         * @throws NullPointerException */
    public Account editAccount(Account account) throws NullPointerException {
        Optional<Account> acc = accountDAO.findById(account.getAccountId());
        if (acc.isEmpty()) {
            throw new NullPointerException("Account not found. Please try again");
        }
        return accountDAO.save(account);
    }
}
