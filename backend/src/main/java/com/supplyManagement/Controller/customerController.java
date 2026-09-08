package com.supplyManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.Customer;
import com.supplyManagement.Service.Customer_Service;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Customer", description = "Customer related APIs")

public class customerController {
	@Autowired
	private Customer_Service service;

	/// i)add Customer
	@PostMapping
	public ResponseEntity<ResponseStructure<Customer>> saveCutomerDetails(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	// ii)fetch all supplier details
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> fetchallCustomerDetails() {
		return service.findallcustomer();
	}

	// iii)fetch Customer details by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>> fetchCustomerDetailsById(@PathVariable int id) {
		return service.findCustomerDetailsById(id);
	}

	// iv)update Customer details
	@PutMapping
	public ResponseEntity<ResponseStructure<Customer>> updateCustomerDetails(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}

	// V)delete customer by using id
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCustomerDetails(@PathVariable int id) {
		return service.deleteCustomer(id);
	}

//vi)get Customer by contact
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable long contact) {
		return service.getCustomerByContact(contact);
	}

//vii)Customer details in pagination and sort format
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerDetailsByPagenation_Sort(
			@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
		return service.getCustomerDetailsByPagination_Sort(pageNumber, pageSize, field);
	}

// viii)get  Supplier by Product
	@GetMapping("/order/{orderId}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByorderId(@PathVariable int orderId) {
		return service.getCustomerByorderId(orderId);
	}
}
