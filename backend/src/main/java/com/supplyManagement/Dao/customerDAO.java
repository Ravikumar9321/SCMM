package com.supplyManagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplyManagement.Entity.Customer;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Repository.Customer_Repository;
import com.supplyManagement.Repository.Product_Repository;

@Repository
public class customerDAO {
     @Autowired
   private Customer_Repository cr;
     
  //i)save Customer
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return cr.save(customer);
	}
//ii)get all customer
	public List<Customer> findallCustomer() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}
	
//	iii)find Customer by id
	public Optional<Customer> findCustomerById(int id) {
		return cr.findById(id);
	}
	
//	public Customer updateCustomerDetails(Customer customer) {
//		return 
//	}
	
//v)delete Customer
	public void deleteCustomer(Customer customer) {
		cr.delete(customer);
		
	}
	//vi)get Customer by contact
	public Customer findCustomerByContact(long contact) {
		return cr.findCustomerBycontact(contact);
	}
	
	//viii)get customer by Paginaton and sort
	public Page<Customer> getCustomerByPagenation_Sort(int pageNumber, int pageSize, String field) {
		
		return cr.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
	public Customer findCustomerByOrderId(int orderId) {
		return cr.getCustomerByOrderId(orderId);
	}

}
