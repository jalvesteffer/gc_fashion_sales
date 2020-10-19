package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{
	
	// By extending JpaRepository, we gain access to predefined query methods
	// like .findAll() and .findById()
	
	
	// Define Spring Data JPA Derived Queries from method names
	List<Product> findByCatId(Long catId);
	List<Product> findByProductId(Long productId);
}
