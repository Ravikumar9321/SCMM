package com.supplyManagement.Dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Repository;
import com.supplyManagement.Entity.Customer;

import com.supplyManagement.Repository.Customer_Repository;


@Repository
public class customerDAO {
     @Autowired
   private Customer_Repository cr;
     
  //i)save Customer
	public Customer saveCustomer(Customer customer) {
		return cr.save(customer);
	}
//ii)get all customer
	public List<Customer> findallCustomer() {
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
