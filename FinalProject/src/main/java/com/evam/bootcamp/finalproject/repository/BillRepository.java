package com.evam.bootcamp.finalproject.repository;

import com.evam.bootcamp.finalproject.entity.Bill;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
	
}
