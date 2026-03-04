package com.supplyManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplyManagement.Entity.Customer;
import com.supplyManagement.Entity.Supplier;


public interface Customer_Repository  extends JpaRepository<Customer, Integer>{
	  
//get Customer details by contact
	@Query("select c from Customer c where c.contact=?1")
	Customer findCustomerBycontact(Long contact);
	
//get Customer by Order id
	@Query("select o.customer from Orders o where o.id=?1")
	Customer getCustomerByOrderId(Integer orderId);
	
	
	
}
