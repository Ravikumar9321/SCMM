package com.supplyManagement.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.Customer;
import com.supplyManagement.Entity.Orders;
import com.supplyManagement.Entity.Product;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Service.Customer_Service;

@RestController
@RequestMapping("/api/customer")
public class customerController {
	@Autowired
	private Customer_Service service;
	
	///i)add Product
		@PostMapping
		public ResponseEntity<ResponseStructure<Customer>> saveCutomerDetails( @RequestBody Customer customer){
		  return service.saveCustomer(customer);
		}

 //ii)fetch all supplier details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Customer>>> fetchallCustomerDetails() {
			return service.findallcustomer();
		}
		
		//iii)fetch  Customer details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Customer>> fetchCustomerDetailsById(@PathVariable int id) {
			return service.findCustomerDetailsById(id);
		}
		
		//iv)update Customer details
		@PutMapping
		public ResponseEntity<ResponseStructure<Customer>> updateCustomerDetails(@RequestBody Customer customer){
			return service.updateCustomer(customer);
		}
		
		//V)delete customer by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteCustomerDetails(@PathVariable int id){
			return service.deleteCustomer(id);
		}
		
		
//vi)get Customer by contact
		@GetMapping("/contact/{contact}")
		public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable long contact){
			  return 	service.getCustomerByContact(contact);
			}
		
//vii)Customer details in pagination and sort format
		   @GetMapping("/{pageNumber}/{pageSize}/{field}")
		   public  ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerDetailsByPagenation_Sort(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
			   return service.getCustomerDetailsByPagination_Sort(pageNumber,pageSize,field);
		   }
		   
// viii)get  Supplier by Product
			@GetMapping("/order/{orderId}")
			public ResponseEntity<ResponseStructure<Customer>> getCustomerByorderId(@PathVariable int orderId){
			  return 	service.getCustomerByorderId(orderId);
			}
}
