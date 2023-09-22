package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.advice.ErrorResponse;
import com.dao.ProductDAO;
import com.data.Product;
import com.exception.ProductNotFoundException;
import com.service.ProductService;


@RestController
//@CrossOrigin(origins="http://localhost:4200",methods = {RequestMethod.GET})
@CrossOrigin(origins="http://localhost:8081",methods = {RequestMethod.GET})
public class ProductController {
	@Autowired
	ProductService ProductService;
	

	
	@GetMapping("/products")
	public List<Product> showAllAcc()
	{
		List<Product> alist=ProductService.getAllProducts();
		return alist;
	}
	
	 @PostMapping("/products")
	  public Product newAccount(@Valid @RequestBody Product newProduct) {
		 return ProductService.saveProduct(newProduct);
	  }
	   
	 @GetMapping("/products/{id}")
	  public Product showByAid(@PathVariable Integer id)  {

		 return ProductService.getProductById(id);
	  }

	 @GetMapping("/productsByCust/{cust}")
	  public List<Product> showByName(@PathVariable String cust)  {

		 if(cust==null || cust.isEmpty()) {
			 throw new InputMismatchException("Product can not be Empty");
		 }
		 else
			 return ProductService.getProductsByName(cust) ;
	  }

	 @GetMapping("/productsByBalance/{minbal}/{maxbal}")
	  public List<Product> showByPriceRange(@PathVariable double minbal,@PathVariable double maxbal) throws ProductNotFoundException {

		 if(minbal<0 || maxbal<0 || minbal>maxbal) {
			 throw new InputMismatchException("Enter proper range of balance");
		 }
		 else
			  return ProductService.getProductsByPriceRange(minbal, maxbal);
	  }
	    
	 @PutMapping("/products/{id}")
	  public Product replaceProduct(@Valid @RequestBody Product newProduct, @PathVariable Integer id) {
		 
	
	        return ProductService.updateProduct(newProduct,id);

	  }
	        
	 @DeleteMapping("/products/{id}")
	  public void deleteProduct(@PathVariable Integer id) {
		 ProductService.deleteProduct(id);
	  }
	 
	 
	 // local to the RestController
	 @ExceptionHandler(InputMismatchException.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Server Error From controller", details);
	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
