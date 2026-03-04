package com.supplyManagement.Contoller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.*;
import com.supplyManagement.Service.Product_Service;

@RestController
@RequestMapping("/api/product")
public class productController {
	
	@Autowired
	private Product_Service service;
	///i)add Product
	@PostMapping("/{supplierId}")
	public ResponseEntity<ResponseStructure<Product>> addProducts(@PathVariable int supplierId,@RequestBody Product product){
	  return service.addProducts(supplierId,product);
	}
	//ii)get Product by  id
	@GetMapping("/{Id}")
	public ResponseEntity<ResponseStructure<Product>> getProductsById(@PathVariable int Id){
	  return 	service.getProductsById(Id);
	}
	//iii)get all Products
	@GetMapping()
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(){
	  return service.getAllProducts();
}
	
	//iv)  Update Product
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
	        return service.updateProduct(product);
	}
	
	///V)  Delete Product
			@DeleteMapping("/{id}")
			public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable Integer id){
				return service.deleteProduct(id);
			}
			
			
//vi)get Product by Supplier id
			@GetMapping("/supplier/{supplierId}")
			public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(@PathVariable int supplierId){
			  return 	service.getProductsBySupplierId(supplierId);
			}
			
			
 //7)Pagination and Sorting format in Products
			@GetMapping("/{pageNumber}/{pageSize}/{field}")
			public ResponseEntity<ResponseStructure<Page<Product>>> getProductsByPagenation_Sort(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field) {
				return service.getProductByPagenation_Sort(pageNumber,pageSize,field);
			}
			
//8)get Products by stock Quantity
			@GetMapping("/quantity/{stockQuantity}")
			public ResponseEntity<ResponseStructure<List<Product>>> getProductsBystockQuantity(@PathVariable Integer stockQuantity){
				  return 	service.getProductsBystockQuantity(stockQuantity);
				}
				
			}
