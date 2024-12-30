package com.revature.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
    
}
