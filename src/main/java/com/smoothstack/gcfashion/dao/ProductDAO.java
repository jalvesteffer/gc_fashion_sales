package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{
	List<Product> findByCatId(Long catId);
	List<Product> findByProductId(Long productId);
}
