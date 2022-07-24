package com.evam.bootcamp.finalproject.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evam.bootcamp.finalproject.entity.Customer;
import com.evam.bootcamp.finalproject.entity.Payment;
import com.evam.bootcamp.finalproject.repository.CustomerRepository;
import com.evam.bootcamp.finalproject.repository.PaymentRepository;

@Service
public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Constructors using Fields
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	/**
	 * Queries all payments in the table from the Payment Repository.
	 * @return the list of the payments.
	 */
	public List<Payment> getPayment() {
		return paymentRepository.findAll();
	}
	
	/**
	 * Creates a specific new payment in the table from the Payment Repository.
	 * @return
	 */
    public void create(Long subsNumber, Payment toAdd) {
    	Customer customer = customerRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
    	toAdd.setCustomer(customer);
    	paymentRepository.save(toAdd);
    }
    
    /**
	 * Deletes the specific payment in the table from the Payment Repository.
	 * @return
	 */
    public void delete(Long subsNumber) {
    	paymentRepository.deleteById(subsNumber);
    }
    
    /**
	 * Queries the table according to the primary key from the Payment Repository.
	 * @return
	 */
    public Payment findById(Long subsNumber) {
        return this.paymentRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Payment Not Found."));
    }
    
}
