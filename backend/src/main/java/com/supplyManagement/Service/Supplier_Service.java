package com.supplyManagement.Service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.supplyManagement.Dao.productDAO;
import com.supplyManagement.Dao.supplierDAO;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.Product;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Exception.IdNotFoundException;
import com.supplyManagement.Exception.NoRecordFoundException;


@Service
public class Supplier_Service {
	@Autowired
	private supplierDAO sdao;
	
	@Autowired
	private productDAO pdao;
///i)save supplier details
	public  ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier) {
		ResponseStructure<Supplier> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Supplier Details Saved");
		rs.setData(sdao.saveSupplier(supplier));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);

	}
  //ii)fetch all supplier details
	public ResponseEntity<ResponseStructure<List<Supplier>>> fetchallSupplier() {
		ResponseStructure<List<Supplier>> rs=new ResponseStructure<>();
		     List<Supplier> ls=sdao.getallSupplier();
		     if(ls.size()>0) {
		    	 rs.setStatusCode(HttpStatus.OK.value());
		    	 rs.setMessage("suppliers details are retrieved");
		    	 rs.setData(ls);
		    	 return new ResponseEntity<>(rs,HttpStatus.OK);
		     }
		     else
		    	 throw new NoRecordFoundException("No Records Found");
		}
	
	  //iii)fetch  supplier details by id
	public ResponseEntity<ResponseStructure<Supplier>> fetchSupplierById(int id) {
		ResponseStructure<Supplier> rs=new ResponseStructure<>();
	     Optional<Supplier> supplier =sdao.getSupplierById(id);
	     if(supplier.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("suppliers details are retrieved by supplierId");
	    	 rs.setData(supplier.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Supplier Id not Found");
	}
	
	//iv)update supplier details
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier) {
		ResponseStructure<Supplier> rs=new ResponseStructure<>();

            if(supplier.getId()==null) {
          	  rs.setStatusCode(HttpStatus.CREATED.value());
          	  rs.setMessage("Supplier Details Saved as new Record");
         		rs.setData(sdao.saveSupplier(supplier));
         		return new ResponseEntity<>(rs,HttpStatus.CREATED);
          }
	     		 Optional<Supplier> opt=sdao.getSupplierById(supplier.getId());

              if(opt.isPresent()) {
            	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("supplier details are updated");
     	    	 rs.setData(sdao.saveSupplier(supplier));
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
              }
              else
            	  throw new IdNotFoundException("Invalid ,Id is not Found");
}
	
	//v)delete supplier details
	public ResponseEntity<ResponseStructure<String>> deleteSupplier(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
		    Optional<Supplier> opt=sdao.getSupplierById(id);
		    if(opt.isPresent()) {
		    	  rs.setStatusCode(HttpStatus.OK.value());
	     	    	 rs.setMessage("supplier details deleted");
	     	    	 sdao.deleteSupplier(opt.get());
	     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
		    }
		    else
          	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
	
	//vi)get Details in pagenaton ,sort format
	public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPagination_Sort(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Supplier>> rs=new ResponseStructure<>();
	    Page<Supplier> l=sdao.getSupplierByPagenatio_Sort(pageNumber,pageSize,field);
		   if(!l.isEmpty()) {
			   rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Recordsfounded");
				rs.setData(l);
				return new ResponseEntity<>(rs,HttpStatus.OK);
		   }
		   else {
			  throw new NoRecordFoundException("No records Found");
		   }
	}
	
	//7)get Supplier by ProductId
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProductId(int productId) {
		ResponseStructure<Supplier> rs=new ResponseStructure<>();
	       Optional<Product>  optproduct=pdao.findProductbyId(productId);
		   if(optproduct.isPresent()) {
	        rs.setStatusCode(HttpStatus.OK.value());
	        rs.setMessage("Supplier Details are retrived by Product Id--"+productId);
	        rs.setData(sdao.getSupplierByProductId(productId));
	        	return new ResponseEntity<>(rs,HttpStatus.OK);
	}
		   else
			   throw new IdNotFoundException("Invalid,Product Id not Found");
	
	}
	}
	
	
	
		
	


