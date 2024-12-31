package com.revature.daos;

import com.revature.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO extends JpaRepository<Payment , Integer> {
}
