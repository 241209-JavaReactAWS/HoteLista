package com.revature.daos;

import com.revature.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
    Optional<Account> findByEmail(String email);
    List<Account> findByRole(boolean isOwner);
}
