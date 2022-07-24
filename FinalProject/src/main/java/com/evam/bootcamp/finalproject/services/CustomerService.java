package com.evam.bootcamp.finalproject.services;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.evam.bootcamp.finalproject.entity.Customer;
import com.evam.bootcamp.finalproject.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	//Constructors using Fields
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	/**
	 * Queries all customers in the table from the Customer Repository.
	 * @return the list of the customers.
	 */
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	/**
	 * Creates a new customer in the table from the Customer Repository.
	 * @return
	 */
    public void create(Customer toAdd) {
        customerRepository.save(toAdd);
    }
    
    /**
	 * Deletes the specific customer in the table from the Customer Repository.
	 * @return
	 */
    public void delete(Long subsNumber) {
        customerRepository.deleteById(subsNumber);
    }
    
    /**
	 * Queries the table according to the primary key from the Customer Repository.
	 * @return
	 */
    public Customer findById(Long subsNumber) {
    	return this.customerRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
    }
    
    /**
  	 * Updates the customer in the table from the Customer Repository.
  	 * @return
  	 */
    @Transactional
    public void update(String name, String surname, Long subsNumber) {
         Customer customerToUpdate = this.customerRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
         if (Objects.nonNull(name) && name.length() > 0 && !customerToUpdate.getName().equals(name))
        	 customerToUpdate.setName(name);
         
         if (Objects.nonNull(surname) && surname.length() > 0 && !customerToUpdate.getSurname().equals(surname))
        	 customerToUpdate.setSurname(surname);
    }

}
