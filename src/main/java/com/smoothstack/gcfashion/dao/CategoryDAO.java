package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
	List<Category> findByCatId(Long catId);
}
