package com.revature.daos;

import com.revature.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
    Account findByEmail(String email);
    List<Account> findByRole(boolean isOwner);
}