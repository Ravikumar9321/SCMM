package com.supplyManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplyManagement.Entity.Product;

public interface Product_Repository  extends JpaRepository<Product, Integer>{
	

	
	//get Products by using supplier id
	@Query("select s.products from Supplier s where s.id=?1")
    List<Product> getProductsBySupplierId(Integer id);
	
	//get Products by using stockQuantity 
		@Query("select p from Product p where p.stockQuantity=?1")
	    List<Product> getProductsByStockQuantity(Integer stockQuantity);
}