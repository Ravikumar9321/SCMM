package com.supplyManagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplyManagement.Entity.Orders;
import com.supplyManagement.Repository.Orders_Repository;

@Repository
public class ordersDAO {
	
	@Autowired
	private Orders_Repository or;
	
//1)create Orders
	public Orders saveOrder(Orders orders) {
		return or.save(orders);
	}
//2)get Order details by Id
	public Optional<Orders> getOrderDetailsById(int id) {
		return or.findById(id);
	}
	
//3)get all Order details 
	public List<Orders> getallOrderDetails() {
		return or.findAll();
	}
//5)delete Order details
	public void deleteOrderDetails(Orders orders) {
		 or.delete(orders);
	}
//6)get Order By TrackNumber
	public Orders getOrderByTrackNumber(String trackNumber) {
	     return	or.getOrderBytrackingNumber(trackNumber);
	}
	
//7)get Order By totalAmount
	public List<Orders> getOrderByAmountGreater(Double amount) {
		return or.getOrderByAmountGreater(amount);
	}
	
//8)get Order By status
	public List<Orders> getOrderByStatus(String status) {
		return or.getOrderByStatus(status);
	}
//9)get Order by customer Id
	public List<Orders> getOrdersbyCustomerId(Integer customerId) {
		return or.getOrderByCustomer(customerId);
	}
	
//10)get Orders by pagination and Sort
	public Page<Orders> getOrdersByPagenation_Sort(int pageNumber, int pageSize, String field) 
	{
		return or.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}

}
