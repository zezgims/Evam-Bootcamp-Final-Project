package com.evam.bootcamp.finalproject.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@SequenceGenerator (
			name = "payment_sequence",
			sequenceName = "payment_sequence", 
			initialValue = 500
	)
    @GeneratedValue (
    		strategy = GenerationType.SEQUENCE,
    		generator = "payment_sequence"
    )
	private Long paymentNumber;
	
	private double totalAmount;
	private LocalDate paymentProcessDate;
	
	
	//Relationship with customer table
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerSubsNumber")
	private Customer customer;
	
	
	//Constructors using Fields
	public Payment() {
		this.paymentProcessDate = LocalDate.now();
	}
	
	public Payment(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	//Getters and Setters
	public Long getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(Long paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getPaymentProcessDate() {
		return paymentProcessDate;
	}
	public void setPaymentProcessDate(LocalDate paymentProcessDate) {
		this.paymentProcessDate = paymentProcessDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	//hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(paymentNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(paymentNumber, other.paymentNumber);
	}

}
