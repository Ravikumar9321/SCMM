package com.supplyManagement.Dao;



import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Repository.Supplier_Repository;

@Repository
public class supplierDAO {
	@Autowired
	private Supplier_Repository sr;

//i)save supplier
	public Supplier saveSupplier(Supplier supplier) {
		return sr.save(supplier);
	}
//ii)fetch all supplier details
	public List<Supplier> getallSupplier() {
		return sr.findAll();
	}

	//iii)get supplier details by id
	public Optional<Supplier> getSupplierById(int id) {
		// TODO Auto-generated method stub
		return sr.findById(id);
	}
	
	//v)delete Supplier details
	public void deleteSupplier(Supplier supplier) {
	       sr.delete(supplier);
	}
	
	//vi)get Details in pagenaton ,sort format
	public Page<Supplier> getSupplierByPagenatio_Sort(int pageNumber, int pageSize, String field) {
		return sr.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
	//7)get Supplier by ProductId
	public Supplier getSupplierByProductId(int productId) {
		return sr.getSupplierByProductId(productId);
	}
	
}

    
    

