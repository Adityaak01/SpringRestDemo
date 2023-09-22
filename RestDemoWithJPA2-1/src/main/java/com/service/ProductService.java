package com.service;

import java.util.List;

import com.data.Product;
import com.exception.ProductNotFoundException;

public interface ProductService {
	public  Product saveProduct(Product ac);
	public Product updateProduct(Product newProduct,int id);
	public void deleteProduct(int pid);
	
	public Product getProductById(int pid);
	
	public List<Product> getAllProducts();
	
	public List<Product> getProductsByName(String cust) ;
	
	public List<Product> getProductsByPriceRange(double minbal,double maxbal) ;
	
}
