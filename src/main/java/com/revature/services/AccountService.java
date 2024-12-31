package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    /** Register a new User Account 
         * @throws Exception */
        public Account registerNewAccount(Account account, Hotel hotel) throws Exception {
        Optional<Account> acc = accountDAO.findById(account.getAccountId());
        if (acc.isPresent()) {
            throw new Exception("This account already exists. Please register with new information");
        }
        /** boolean isOwner evaluates to true, meaning a hotel owner is trying to register */
        if (acc.get().getRole()) {
            /** Register the hotel owner and save the new hotel to hotel table, modify if hotel already exists */
            hotelDAO.save(hotel);
        }
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
