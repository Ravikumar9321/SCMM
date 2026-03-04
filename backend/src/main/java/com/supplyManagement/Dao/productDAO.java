package com.supplyManagement.Dao;

import java.util.List
;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplyManagement.Entity.Product;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Repository.Product_Repository;

@Repository
public class productDAO {
	@Autowired
	Product_Repository pr;

	//i)add product
	public Product addProduct(Product product) {
       return	pr.save(product);
	}

	
// ii)get all products
	public List<Product> getallProduct() {
		return pr.findAll();
	}
//iii)get Product by id
	public Optional<Product> findProductbyId(int id) {
		return pr.findById(id);
	}
	
//iv)update Product
	public Product updateProduct(Product product) {
		 return pr.save(product);
	}
//v)delete Product
	public void deleteProduct(Product product) {
		  pr.delete(product);
	}
	

//	//vi)get Product by supplier id
	public List<Product> getProductsBySupplierId(Integer supplierId) {
		return pr.getProductsBySupplierId(supplierId);
	}

// 7)get Product by Pagination and Sort
	public Page<Product> getProductByPagenation_Sort(int pageNumber, int pageSize, String field) {
		// TODO Auto-generated method stub
		return pr.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}

//8)get Products by  stockQuality
	public List<Product> getProductBystockQuantity(Integer stockQuantity) {
		// TODO Auto-generated method stub
		return pr.getProductsByStockQuantity(stockQuantity);
	}

}
