package com.supplyManagement.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.supplyManagement.Dao.*;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.*;
import com.supplyManagement.Exception.IdNotFoundException;
import com.supplyManagement.Exception.NoRecordFoundException;

@Service
public class Product_Service {
	@Autowired
	private productDAO pdao;
	@Autowired
	private supplierDAO sdao;

	//i)add products
	public ResponseEntity<ResponseStructure<Product>> addProducts( int supplierId,Product product) {
		ResponseStructure<Product> rs=new ResponseStructure<>();
		       Optional<Supplier>  optsupplier=sdao.getSupplierById(supplierId);
		       
	    	   if(optsupplier.isPresent()) {
			    	   Supplier supplier=optsupplier.get();//convet Optional class-->Supplier class
                    product.setSupplier(supplier);
		    	   rs.setStatusCode(HttpStatus.CREATED.value());
		    	   rs.setMessage("Product saved with supplierId"+supplier.getId());
		    	   rs.setData( pdao.addProduct(product));
		    	   return new ResponseEntity<>(rs,HttpStatus.CREATED);
		       }
		       else
		    	   throw new IdNotFoundException("Invalid,Supplier Id not Found");
	}
		
	
     //ii)fetch Product by id
		public ResponseEntity<ResponseStructure<Product>> getProductsById(int id) {
			ResponseStructure<Product> rs=new ResponseStructure<>();
		      Optional<Product> opt=pdao.findProductbyId(id);
		      if(opt.isPresent()) {
		    	  rs.setStatusCode(HttpStatus.OK.value());
			      rs.setMessage("Product Founded");
			      rs.setData(opt.get());
					return new ResponseEntity<ResponseStructure<Product>>(rs,HttpStatus.OK);

		      }
		      else 
		    	  throw  new IdNotFoundException("Invalid,Id not Found");
		}
		
		
		//iii)fetch all product details
				public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts( ) {
					ResponseStructure<List<Product>> rs=new ResponseStructure<>();
					     List<Product> ls=pdao.getallProduct();
					     if(ls.size()>0) {
					    	 rs.setStatusCode(HttpStatus.OK.value());
					    	 rs.setMessage("Products  are retrieved");
					    	 rs.setData(ls);
					    	 return new ResponseEntity<>(rs,HttpStatus.OK);
					     }
					     else
					    	 throw new NoRecordFoundException("No Records Found");
					}

  ///iv)update Product
				public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
					ResponseStructure<Product> rs=new ResponseStructure<>();
					   
                       if(product.getId()==null) {
                    		rs.setStatusCode(HttpStatus.CREATED.value());
    			    		rs.setMessage(" new Product added successfully");
//   			    	    product.setSupplier();
    			    	    rs.setData(pdao.addProduct(product));  
    						return new ResponseEntity<>(rs,HttpStatus.CREATED);
                       }
  			         Optional<Product> opt=pdao.findProductbyId(product.getId());
 					Supplier supplier=opt.get().getSupplier();
			          if(opt.isPresent()) {
			      		rs.setStatusCode(HttpStatus.OK.value());
			    		rs.setMessage("Product Details Updated");
			    	    product.setSupplier(supplier);
			    	    rs.setData(pdao.updateProduct(product));  
						return new ResponseEntity<>(rs,HttpStatus.OK);

			          }
			         else {
			        	 throw new IdNotFoundException("Invalid ,because We cannot insert new Id");
			         }
				   	
				}

//v)delete Product
				public ResponseEntity<ResponseStructure<String>> deleteProduct(Integer id) {
					ResponseStructure<String> rs=new ResponseStructure<>();
					
					Optional<Product> opt=pdao.findProductbyId(id);
					if(opt.isPresent()) {
				          pdao.deleteProduct(opt.get());
				          rs.setStatusCode(HttpStatus.OK.value());
							rs.setMessage("Product has been Deleted");
							return new ResponseEntity<>(rs,HttpStatus.OK);
					}
					else 
		            	  throw new IdNotFoundException("Invalid ,Id is not Found");
				}

	//6)get Products by using supplier Id
				public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(Integer supplierId) {
					ResponseStructure<List<Product>> rs=new ResponseStructure<>();
				       Optional<Supplier>  optsupplier=sdao.getSupplierById(supplierId);
					   if(optsupplier.isPresent()) {
				        rs.setStatusCode(HttpStatus.OK.value());
				        rs.setMessage("Products are retrieved based on Supplier Id--"+supplierId);
				        rs.setData(pdao.getProductsBySupplierId(supplierId));
				        	return new ResponseEntity<>(rs,HttpStatus.OK);
				}
					   else
						   throw new IdNotFoundException("Inalid,Supplier Id not Found");
				
				}


///7)Pagination and Sorting in Product format
				public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPagenation_Sort(int pageNumber,
						int pageSize, String field) {
					ResponseStructure<Page<Product>> rs=new ResponseStructure<>();
				    Page<Product> l=pdao.getProductByPagenation_Sort(pageNumber,pageSize,field);
					   if(!l.isEmpty()) {
						   rs.setStatusCode(HttpStatus.OK.value());
							rs.setMessage("Records retrieved by Pagination and Sort format");
							rs.setData(l);
							return new ResponseEntity<>(rs,HttpStatus.OK);
					   }
					   else {
						  throw new NoRecordFoundException("No records Found");
					   }
				}


//8)get Products by  using stockQuantity
	public ResponseEntity<ResponseStructure<List<Product>>> getProductsBystockQuantity(Integer stockQuantity) 
	{
					ResponseStructure<List<Product>> rs=new ResponseStructure<>();
				       List<Product>  ls=pdao.getProductBystockQuantity(stockQuantity);
					   if(ls.size()>0) {
				        rs.setStatusCode(HttpStatus.OK.value());
				        rs.setMessage("Products are retrieved based on stockQuantity--");
				        rs.setData(ls);
				        	return new ResponseEntity<>(rs,HttpStatus.OK);
				}
					   else
						   throw new NoRecordFoundException(" Products not Found");
				
				}
			}




		
	
	

