package com.evam.bootcamp.finalproject.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.evam.bootcamp.finalproject.services.CustomerService;
import com.evam.bootcamp.finalproject.entity.Customer;

@RestController
@RequestMapping("/customers")
@RequestScope
public class CustomerController { 
	
	private final CustomerService customerService;
	
	//Constructor using Fields
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    /**
     * Queries all customers in the table from the Customer Service.
     * @return the list of the customers.
     */
	@GetMapping("/getAll")
    public List<Customer> getAll() {
        return this.customerService.getCustomers();
    }
    
	/**
     * Makes a specific customer query in the table from the Customer Service.
     * @return the created ResponseEntity.
     */
    @GetMapping("/getCustomer/{subsNumber}")
    public ResponseEntity<?> getById(@PathVariable("subsNumber") Long subsNumber) {
        try{
            return ResponseEntity.ok(this.customerService.findById(subsNumber));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Creates a new customer in the table from the Customer Service.
     * @return the created ResponseEntity.
     */
    @PostMapping("/createCustomer")
    public ResponseEntity<?> add(@RequestBody Customer customer) {
        this.customerService.create(customer);
        return ResponseEntity.ok("Customer created.");
    }
    
    /**
     * Deletes the specific customer in the table from the Customer Service.
     * @return the created ResponseEntity.
     */
    @DeleteMapping("/deleteCustomer/{subsNumber}")
    public ResponseEntity<?> delete(@PathVariable("subsNumber") Long subsNumber) {
        try{
            this.customerService.delete(subsNumber);
            return ResponseEntity.ok().body("Customer deleted.");
        } catch (EntityNotFoundException e){
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("The customer could not be deleted.");
        }
    }
    
    /**
     * Updates the customer in the table from the Customer Service.
     * @return the created ResponseEntity.
     */
    @PutMapping("/updateCustomer/{subsNumber}")
    public ResponseEntity<?> update(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                    @PathVariable("subsNumber") Long subsNumber) {
        try {
        	this.customerService.update(name, surname, subsNumber);
        	return ResponseEntity.ok("Customer updated.");
        }catch (Exception e){
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
}
