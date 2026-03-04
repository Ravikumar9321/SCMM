package com.supplyManagement.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.supplyManagement.Dao.*;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.*;
import com.supplyManagement.Exception.*;

@Service
public class Orders_Service {

	@Autowired
	private ordersDAO odao;

	@Autowired
	private customerDAO cdao;
	@Autowired
	private productDAO pdao;

//1)create Orders
	public ResponseEntity<ResponseStructure<Orders>> createOrders(Orders orders, Integer customerId) {
		ResponseStructure<Orders> rs = new ResponseStructure<>();
		Optional<Customer> optcustomer = cdao.findCustomerById(customerId);
		if (optcustomer.isEmpty()) {
			throw new IdNotFoundException("Invalid,customer Id");
		}
		Customer customer = optcustomer.get();
		// store list of products
		List<Product> getAllproducts = new ArrayList<Product>();

		double totalAmount = 0.0;
		
		if (orders.getProducts().isEmpty()) {
			throw new NoRecordFoundException("Product Records Not Found");
		}
		for (Product p : orders.getProducts()) {
			Optional<Product> optproduct = pdao.findProductbyId(p.getId());
			if (optproduct.isPresent()) {
				Product products = optproduct.get();
				getAllproducts.add(products);
				totalAmount = totalAmount + products.getPrice();
			} else
				throw new IdNotFoundException("Invalid,Product id Not Found");
		}
		orders.setCustomer(customer);
		orders.setProducts(getAllproducts);
		orders.setTotalAmount(totalAmount);
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Order created successfully");
		rs.setData(odao.saveOrder(orders));
		return new ResponseEntity<ResponseStructure<Orders>>(rs, HttpStatus.CREATED);
	}

//2)fetch Order details by Id
	public ResponseEntity<ResponseStructure<Orders>> getOrderDetailsById(int id) {
		ResponseStructure<Orders> rs = new ResponseStructure<>();
		Optional<Orders> opt = odao.getOrderDetailsById(id);
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Order details are retrieved by Id");
			rs.setData(opt.get());
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid,Order Id not Found");
	}

//3)fetch all Orders details
	public ResponseEntity<ResponseStructure<List<Orders>>> getallOrderDetails() {
		ResponseStructure<List<Orders>> rs = new ResponseStructure<>();
		List<Orders> ls = odao.getallOrderDetails();
		if (ls.size() > 0) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("orders details are retrieved");
			rs.setData(ls);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException("No Records Found");
	}

//4)update Order details
	public ResponseEntity<ResponseStructure<Orders>> updateOrderDetails(Orders orders) {
		ResponseStructure<Orders> rs = new ResponseStructure<>();
		if (orders.getId() == null) {
			throw new IdNotFoundException("Order id not Found");
		}
		Optional<Orders> opt = odao.getOrderDetailsById(orders.getId());
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Order details are updated");
			rs.setData(odao.saveOrder(orders));
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid ,Id is not Found");
	}

//5)delete Order details
	public ResponseEntity<ResponseStructure<String>> deleteOrderDetails(int id) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		Optional<Orders> opt = odao.getOrderDetailsById(id);
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Order details deleted");
			odao.deleteOrderDetails(opt.get());
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid ,Id is not Found");
	}

//6)get Order details by trackNumber
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackNumber(String trackNumber) {
		ResponseStructure<Orders> rs = new ResponseStructure<>();
		Orders order = odao.getOrderByTrackNumber(trackNumber);
		if (order != null) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Orders are retrieved based on trackingNumber--");
			rs.setData(order);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException(" tracking Number not Found ,Order Details not Found");

	}

	// 7)get Order details by Amount
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByAmountGreater(Double amount) {
		ResponseStructure<List<Orders>> rs = new ResponseStructure<>();
		List<Orders> order = odao.getOrderByAmountGreater(amount);
		if (order.size() > 0) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Orders are retrieved based on Amount greaterthan--");
			rs.setData(order);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException(" Order Details not Found");

	}

	// 8)get Order details by status
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByStatus(String status) {
		ResponseStructure<List<Orders>> rs = new ResponseStructure<>();
		List<Orders> order = odao.getOrderByStatus(status);
		if (order.size() > 0) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Orders are retrieved based on status--");
			rs.setData(order);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException(" Order Details not Found");

	}

//9)get Orders by customer id
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(Integer customerId) {
		ResponseStructure<List<Orders>> rs = new ResponseStructure<>();
		List<Orders> ls;
		Optional<Orders> opt = odao.getOrderDetailsById(customerId);
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Orders are retrieved by CustomerId--");
			ls = odao.getOrdersbyCustomerId(customerId);// to avoid returning empty list
			if (ls.size() > 0) {
				rs.setData(ls);
			} else {
				throw new NoRecordFoundException("Customer Don't place any Orders");
			}
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid, CustomerId Not Found");

	}

//10)get Orders pagination and Sort format
	public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPagenation_Sort(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Orders>> rs = new ResponseStructure<>();
		Page<Orders> order = odao.getOrdersByPagenation_Sort(pageNumber, pageSize, field);
		if (!order.isEmpty()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Orders details retrieved by Pagination and Sort format");
			rs.setData(order);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException("No records Found");

	}

}
