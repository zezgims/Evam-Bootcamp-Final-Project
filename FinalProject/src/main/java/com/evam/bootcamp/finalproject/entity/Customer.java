package com.evam.bootcamp.finalproject.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@SequenceGenerator (
			name = "customer_sequence",
			sequenceName = "customer_sequence", 
			initialValue = 100
	)
    @GeneratedValue (
    		strategy = GenerationType.SEQUENCE,
    		generator = "customer_sequence"
    )
	private Long subsNumber;
	
	private String name;
	private String surname;
	
	
	//Constructors using Fields
	public Customer() {}
	
	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}


	//Getters and Setters
	public Long getSubsNumber() {
		return subsNumber;
	}
	public void setSubsNumber(Long subsNumber) {
		this.subsNumber = subsNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	//hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(subsNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(subsNumber, other.subsNumber);
	}
	
}
