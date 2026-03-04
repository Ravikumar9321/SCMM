package com.supplyManagement.Contoller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.*;
import com.supplyManagement.Service.Orders_Service;


@RestController
@RequestMapping("/api/orders")
public class ordersController {
	@Autowired
	private Orders_Service service;
	
//1)create Order
	@PostMapping("/{customerId}")
	public ResponseEntity<ResponseStructure<Orders>> createOrder(@RequestBody Orders orders,@PathVariable Integer customerId) {
		 return service.createOrders(orders,customerId);
	}

 //2)fetch  Order details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Orders>> fetchOrderDetailsById(@PathVariable int id) {
			return service.getOrderDetailsById(id);
		}
		
 //3)fetch all Order details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Orders>>> fetchallOrderDetails() {
			return service.getallOrderDetails();
		}
		
//4)update Supplier details
		@PutMapping
		public ResponseEntity<ResponseStructure<Orders>> updateOrderDetails(@RequestBody Orders orders){
			return service.updateOrderDetails(orders);
		}
		
//5)delete Order details
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteOrderDetails(@PathVariable int id){
			return service.deleteOrderDetails(id);
		}
		
		
//6)get Order by tracking number
			@GetMapping("/track/{trackNumber}")
			public ResponseEntity<ResponseStructure<Orders>> getOrderBytrackNumber(@PathVariable String trackNumber){
				  return 	service.getOrderByTrackNumber(trackNumber);
				}
			
//7)get Order by price Greater
			@GetMapping("/amount/{amount}")
		    public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByAmountgreater(@PathVariable Double amount){
				  return 	service.getOrderByAmountGreater(amount);
	}		
			
//8)get Order by status
			@GetMapping("/status/{status}")
		    public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByStatus(@PathVariable String status){
				  return 	service.getOrderByStatus(status);
	}		
			
//9)get Orders by customer
			@GetMapping("/customer/{customerId}")
		    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(@PathVariable Integer customerId){
				  return 	service.getOrdersByCustomer(customerId);
	}		

//10) get Orders Pagination and Sorting format 
			@GetMapping("/{pageNumber}/{pageSize}/{field}")
			public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPagenation_Sort(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field) {
				return service.getOrdersByPagenation_Sort(pageNumber,pageSize,field);
			}
}
