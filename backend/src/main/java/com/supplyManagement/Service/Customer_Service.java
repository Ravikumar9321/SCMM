package com.supplyManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplyManagement.Dao.customerDAO;
import com.supplyManagement.Dao.ordersDAO;
import com.supplyManagement.Dto.ResponseStructure;
import com.supplyManagement.Entity.Customer;
import com.supplyManagement.Entity.Orders;
import com.supplyManagement.Entity.Product;
import com.supplyManagement.Entity.Supplier;
import com.supplyManagement.Exception.IdNotFoundException;
import com.supplyManagement.Exception.NoRecordFoundException;

@Service
public class Customer_Service {
	
	@Autowired 
	private customerDAO cdao;
	
	@Autowired 
	private ordersDAO odao;

	//i)save customer details
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
			ResponseStructure<Customer> rs=new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Customer Details Saved");
			rs.setData(cdao.saveCustomer(customer));
			return new ResponseEntity<>(rs,HttpStatus.CREATED);

		}
	
	//ii)fetch all Customer details
	public ResponseEntity<ResponseStructure<List<Customer>>> findallcustomer() {
			ResponseStructure<List<Customer>> rs=new ResponseStructure<>();
			     List<Customer> ls=cdao.findallCustomer();
			     if(ls.size()>0) {
			    	 rs.setStatusCode(HttpStatus.OK.value());
			    	 rs.setMessage("Customer details are retrieved");
			    	 rs.setData(ls);
			    	 return new ResponseEntity<>(rs,HttpStatus.OK);
			     }
			     else
			    	 throw new NoRecordFoundException("No Details Found");
			}
	
//iii)find customer  Id
	public ResponseEntity<ResponseStructure<Customer>> findCustomerDetailsById(int id) {
		ResponseStructure<Customer> rs=new ResponseStructure<>();
	     Optional<Customer> supplier =cdao.findCustomerById(id);
	     if(supplier.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("customer details are retrieved by customer Id");
	    	 rs.setData(supplier.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,customer Id not Found");
	}
	
//iv)update Customer details
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		ResponseStructure<Customer> rs=new ResponseStructure<>();
		 if(customer.getId()==null) {
			 throw new IdNotFoundException("Customer Id Not Found");
		 }

		 Optional<Customer> opt=cdao.findCustomerById(customer.getId());
        if  (opt.isPresent()) {
        	  rs.setStatusCode(HttpStatus.OK.value());
 	    	 rs.setMessage("Customer details are updated");
 	    	 rs.setData(cdao.saveCustomer(customer));
 	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
          }
          else
        	  throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}
//v)delete Customer
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Customer> opt=cdao.findCustomerById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Customer details deleted");
     	    	 cdao.deleteCustomer(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
}

	//vi)get Customer by Contact
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(long contact) {
		ResponseStructure<Customer> rs=new ResponseStructure<>();
	       Customer  customer=cdao.findCustomerByContact(contact);
		   if(customer!=null) {
	        rs.setStatusCode(HttpStatus.OK.value());
	        rs.setMessage("Customer Details are retrieved based on contact--");
	        rs.setData(customer);
	        	return new ResponseEntity<>(rs,HttpStatus.OK);
	}
		   else
			   throw new NoRecordFoundException(" Customer Details not Found");
	
	}
	
//vii)get customer by pagination and sort format
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerDetailsByPagination_Sort(int pageNumber,
			int pageSize, String field) {
			ResponseStructure<Page<Customer>> rs=new ResponseStructure<>();
	    Page<Customer> l=cdao.getCustomerByPagenation_Sort(pageNumber,pageSize,field);
		   if(!l.isEmpty()) {
			   rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Customer Details retrieved by Pagination and Sort format");
				rs.setData(l);
				return new ResponseEntity<>(rs,HttpStatus.OK);
		   }
		   else 
			  throw new NoRecordFoundException("No records Found");
	}

	//viii)get Customer by orderId
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByorderId(int orderId) {
		ResponseStructure<Customer> rs=new ResponseStructure<>();
		  Optional<Orders> opt= odao.getOrderDetailsById(orderId);
		  if(opt.isPresent()) {
			  rs.setStatusCode(HttpStatus.OK.value());
			  rs.setMessage("Customer retrieved by OrderId");
			  rs.setData(cdao.findCustomerByOrderId(orderId));
			  return new ResponseEntity<ResponseStructure<Customer>>(rs,HttpStatus.OK);
		  }
		  else
			  throw new IdNotFoundException("Order Id Not Found");
	}

	
	}
	


