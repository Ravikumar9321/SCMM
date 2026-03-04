package com.supplyManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplyManagement.Entity.Orders;
import com.supplyManagement.Entity.Supplier;

public interface Orders_Repository  extends JpaRepository<Orders, Integer>{
	  
	//6)get Order by trackingNumber
	@Query("select o from Orders o where o.trackingNumber=?1")
	Orders getOrderBytrackingNumber(String trackNumber);

	//7)get Order by price greater than
		@Query("select o from Orders o where o.totalAmount>?1")
		List<Orders> getOrderByAmountGreater(Double price);
		
	//8)get Order by status
		@Query("select o from Orders o where o.status=?1")
		List<Orders> getOrderByStatus(String status);
		
	//9)get Orders by customer
		@Query("select c.orders from Customer c where c.id=?1")
		List<Orders> getOrderByCustomer(Integer customerId);
}
