package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.data.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	// @Query("FROM Account WHERE customer = ?1")
	    List<Product> findByName(String name);
	 
	 @Query("SELECT a FROM Account a WHERE balance between   :minbal AND :maxbal")
	   List<Product> findByPriceBetween(@Param("minbal") double minbal, @Param("maxbal") double maxbal);
}


// /  findBy<<propertyname>>
// find account list by customer in Account class method name is 
//  findByCustomer
/// find by Account by balance property
//  findByBalance