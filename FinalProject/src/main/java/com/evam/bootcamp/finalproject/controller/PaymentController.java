package com.evam.bootcamp.finalproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.evam.bootcamp.finalproject.entity.Payment;
import com.evam.bootcamp.finalproject.services.PaymentService;

@RestController
@RequestMapping("/payments")
@RequestScope
public class PaymentController {
	
	private final PaymentService paymentService;
	
	//Constructor using Fields
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    /**
     * Queries all payments in the table from the Payment Service.
     * @return the list of the payments.
     */
    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return this.paymentService.getPayment();
    }
    
    /**
     * Makes a specific payment query in the table from the Payment Service.
     * @return the created ResponseEntity.
     */
    @GetMapping("/getPayment/{paymentNumber}")
    public ResponseEntity<?> getById(@PathVariable("paymentNumber") Long paymentNumber) {
        try{
            return ResponseEntity.ok(this.paymentService.findById(paymentNumber));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Creates a specific new payment in the table from the Payment Service.
     * @return the created ResponseEntity.
     */
    @PostMapping("/createPayment/{subsNumber}")
    public ResponseEntity<?> add(@PathVariable("subsNumber") Long subsNumber, @RequestBody Payment payment) {
		this.paymentService.create(subsNumber, payment);
        return ResponseEntity.ok("Payment created.");
    }
  
}
