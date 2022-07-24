package com.evam.bootcamp.finalproject.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evam.bootcamp.finalproject.entity.Bill;
import com.evam.bootcamp.finalproject.entity.Customer;
import com.evam.bootcamp.finalproject.repository.BillRepository;
import com.evam.bootcamp.finalproject.repository.CustomerRepository;

@Service
public class BillService {

	private final BillRepository billRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Constructors using Fields
	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}
	
	/**
	 * Queries all bills in the table from the Bill Repository.
	 * @return the list of the bills.
	 */
	public List<Bill> getBills() {
		return billRepository.findAll();
	}
	
	/**
	 * Creates a specific new bill in the table from the Bill Repository.
	 * @return the created ResponseEntity.
	 */
    public void create(Long subsNumber, Bill toAdd) {
    	Customer customer = customerRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
    	toAdd.setCustomer(customer);
    	billRepository.save(toAdd);
    }
    
    /**
	 * Deletes the specific bill in the table from the Bill Repository.
	 * @return the created ResponseEntity.
	 */
    public void delete(Long billNumber) {
    	billRepository.deleteById(billNumber);
    }
    
    /**
	 * Queries the table according to the primary key from the Bill Repository.
	 * @return the created ResponseEntity.
	 */
    public Bill findById(Long billNumber) {
        return this.billRepository.findById(billNumber).orElseThrow(() -> new EntityNotFoundException("Bill Not Found."));
    }
    
    /**
	 * Queries the table according to the foreign key (subsNumber) from the Bill Repository.
	 * @return the created ResponseEntity.
	 */
    public List<Bill> findByCustomer(Long subsNumber) {
    	int paid = 1;
    	
    	if(this.customerRepository.findById(subsNumber).get() == null)
    		throw new EntityNotFoundException("Customer Not Found.");
    	
        List<Bill> listBill = this.billRepository.findAll();
        Iterator<Bill> it = listBill.iterator();
        
        while(it.hasNext()) {
        	Bill bill = it.next();
        	if((bill.getCustomer() == null) || (!bill.getCustomer().getSubsNumber().equals(subsNumber)) || bill.getStatu() == paid) {
        		it.remove();
        	}	
        }
       
    	return listBill;
    }
    
    /**
	 * Updates the statu field in the table from the Bill Repository.
	 * @return the created ResponseEntity.
	 */
    @Transactional
    public void update(Long subsNumber) throws Exception {
         Bill statuToUpdate = this.billRepository.findById(subsNumber).orElseThrow(() -> new EntityNotFoundException("Bill Not Found."));
         if(statuToUpdate.getStatu() == 0) 
        	 statuToUpdate.setStatu(1);
         else
        	 throw new Exception();
    }
    
}
