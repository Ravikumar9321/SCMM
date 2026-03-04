package com.supplyManagement.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.Product;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Service.Supplier_Service;
@RequestMapping("/api/supplier")
@RestController
public class supplierController {
	
	@Autowired
	private Supplier_Service service;
	
	//i) save details
	@PostMapping
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplierDetails(@RequestBody Supplier supplier) {
		return service.saveSupplier(supplier);
	}
    //ii)fetch all supplier details
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Supplier>>> fetchallSupplierDetails() {
		return service.fetchallSupplier();
	}
	
    //iii)fetch  supplier details by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> fetchSupplierDetailsById(@PathVariable int id) {
		return service.fetchSupplierById(id);
	}
	//iv)update Supplier details
	@PutMapping
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplierDetails(@RequestBody Supplier supplier){
		return service.updateSupplier(supplier);
	}
	//v)delete supplier detail
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteSupplierDetails(@PathVariable int id){
		return service.deleteSupplier(id);
	}
	
	//vi)supplier details in pagination and sort format
	   @GetMapping("/{pageNumber}/{pageSize}/{field}")
	   public  ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierDetailsByPagenation_Sort(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
		   return service.getSupplierByPagination_Sort(pageNumber,pageSize,field);
	   }
	   
// vii)get  Supplier by Product
		@GetMapping("/product/{productId}")
		public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProductId(@PathVariable int productId){
		  return 	service.getSupplierByProductId(productId);
		}
}
  
