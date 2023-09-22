package com.data;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product implements java.io.Serializable{
	
	@Id
	//@GeneratedValue 
	private int pid;
	
	@NotBlank(message = "Product name can not be blank")
	@Size(min=2,message="Name should be minimum 2 chars")
	@Column(name="holder")
	private String name;
	
	@Min(value=1,message="Price should be minimum 1")
	private double price;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int aid, String customer, double balance, String email) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
		
	}

	public int getPid() {
		return pid;
	}

	public void setAid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setCustomer(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setBalance(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + "]";
	}
	
	

	
		
}
