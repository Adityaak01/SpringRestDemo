package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.data.Product;
import com.exception.ProductNotFoundException;

@Service("Service")
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	@Transactional
	@Override
	public Product saveProduct(Product ac) {
			return productDAO.save(ac);
	}

	@Transactional
	@Override
	public Product updateProduct(Product newProduct,int id) {
		// TODO Auto-generated method stub
		 Optional<Product> optac=productDAO.findById(id);
		 Product ac=optac.get();
		 if(ac==null) {
				throw new ProductNotFoundException("Could not find Account with id "+id);
			}
//		 System.out.println(ac+ " in update");
		 ac.setAid(newProduct.getPid());
	     ac.setCustomer(newProduct.getName());
	        ac.setBalance(newProduct.getPrice());
	        
	       Product o= productDAO.save(ac);
	//       System.out.println(o + "After update");
		return o;
	}

	@Transactional
	@Override
	public void deleteProduct(int pid) {
		// TODO Auto-generated method stub
		productDAO.deleteById(pid);
	}

	@Transactional
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDAO.findAll();
	}

	@Transactional
	@Override
	public Product getProductById(int pid)  {
	
		Optional<Product> ac=productDAO.findById(pid);
		if(!ac.isPresent()) {
			throw new ProductNotFoundException("Could not find Account with id "+pid);
		}
		else
			return ac.get();
	}

	@Override
	public List<Product> getProductsByName(String cust)  {
		// TODO Auto-generated method stub
		List<Product> alist=productDAO.findByName(cust);
		if(alist.size()==0) {
			throw new ProductNotFoundException("Could not find any Account with customer "+cust);
		}
		else
			return alist;
		
	}

	@Override
	public List<Product> getProductsByPriceRange(double minbal, double maxbal)  {
		List<Product> alist=productDAO.findByPriceBetween(minbal, maxbal);
		if(alist.size()==0) {
			throw new ProductNotFoundException("Could not find any Account in balance range of  "+minbal +" and "+maxbal);
		}
		else
			return alist;
	}
}
