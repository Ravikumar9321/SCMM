package com.supplyManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplyManagement.Entity.Supplier;

public interface Supplier_Repository  extends JpaRepository<Supplier, Integer>{
	  

	//6)get Supplier by Product
	@Query("select p.supplier from Product p where  p.id=?1")
	 Supplier getSupplierByProductId(Integer productId);
}
