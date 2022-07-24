package com.evam.bootcamp.finalproject.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.evam.bootcamp.finalproject.entity.Bill;
import com.evam.bootcamp.finalproject.services.BillService;

@RestController
@RequestMapping("/bills")
@RequestScope
public class BillController {
	
	private final BillService billService;
	
	//Constructors using Fields
    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * Queries all bills in the table from the Bill Service.
     * @return the list of the bills.
     */
    @GetMapping("/getAll")
    public List<Bill> getAll() {
        return this.billService.getBills();
    }
    
    /**
     * Makes a specific bill query in the table from the Bill Service.
     * @return the created ResponseEntity.
     */
    @GetMapping("/getBill/{billNumber}")
    public ResponseEntity<?> getById(@PathVariable("billNumber") Long billNumber) {
        try {
            return ResponseEntity.ok(this.billService.findById(billNumber));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
  
    /**
     * Makes a specific bill query by {subsNumber} in the table from the Bill Service.
     * @return the created ResponseEntity.
     */
    @GetMapping("/getBillByCustomer/{subsNumber}")
    public ResponseEntity<?> getByCustomer(@PathVariable("subsNumber") Long subsNumber) {
        try {
        	List<Bill> billList = this.billService.findByCustomer(subsNumber);
        	if(billList.isEmpty()) {
        		return ResponseEntity.ok().body("There are no unpaid bills.");
        	}
            return ResponseEntity.ok(billList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Creates a specific new bill in the table from the Bill Service.
     * @return the created ResponseEntity.
     */
    @PostMapping("/createBill/{subsNumber}")
    public ResponseEntity<?> add(@PathVariable("subsNumber") Long subsNumber, @RequestBody Bill bill) {
    	this.billService.create(subsNumber, bill);
        return ResponseEntity.ok("Bill created.");
    }
    
    /**
     * Deletes the specific bill in the table from the Bill Service.
     * @return the created ResponseEntity.
     */
    @DeleteMapping("/deleteBill/{billNumber}")
    public ResponseEntity<?> delete(@PathVariable("billNumber") Long billNumber) {
        try {
            this.billService.delete(billNumber);
            return ResponseEntity.ok().body("Bill deleted.");
        } catch (EntityNotFoundException e){
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("The bill could not be deleted.");
        }
    }
    
    /**
     * Updates the statu field in the table from the Bill Service.
     * @return the created ResponseEntity.
     */
    @PutMapping("/payBill/{billNumber}")
    public ResponseEntity<?> update(@PathVariable("billNumber") Long billNumber) {
    	try {
    		this.billService.update(billNumber);
    		return ResponseEntity.ok().body("Bill paid.");
    	} catch (EntityNotFoundException e){
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body("Bill has already been paid.");
    	}
    }
    
}
