package com.revature.daos;

import com.revature.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
    Optional<Account> findByEmail(String email);
    List<Account> findByisOwner(boolean isOwner);
    /** Verifying login */
    @Query("FROM Account WHERE email = :email and password = :password")
    Optional<Account> findByEmailAndPassword(String email, String password);
}
