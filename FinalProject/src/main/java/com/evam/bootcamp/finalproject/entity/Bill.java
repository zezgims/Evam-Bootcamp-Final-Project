package com.evam.bootcamp.finalproject.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
	
	@Id
	@SequenceGenerator (
			name = "bill_sequence",
			sequenceName = "bill_sequence", 
			initialValue = 2000
	)
    @GeneratedValue (
    		strategy = GenerationType.SEQUENCE,
    		generator = "bill_sequence"
    )
	private Long billNumber;
	
	private Double dept;
	private LocalDate billProcessDate;
	private int statu;
	
	
	//Relationship with customer table
	@ManyToOne
	@JoinColumn(name = "customerSubsNumber")
	private Customer customer;
	
	
	//Constructors using Fields
	public Bill() {
		this.billProcessDate = LocalDate.now();
		this.statu = 0;
	}
	
	public Bill(Double dept) {
		this.dept = dept;
	}
	
	
	//Getters and Setters
	public Long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	
	public Double getDept() {
		return dept;
	}
	public void setDept(Double dept) {
		this.dept = dept;
	}

	public LocalDate getBillProcessDate() {
		return billProcessDate;
	}
	public void setBillProcessDate(LocalDate billProcessDate) {
		this.billProcessDate = billProcessDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}


	//hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(billNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		return Objects.equals(billNumber, other.billNumber);
	}
	
}
