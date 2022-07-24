package com.evam.bootcamp.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.evam.bootcamp.finalproject.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}