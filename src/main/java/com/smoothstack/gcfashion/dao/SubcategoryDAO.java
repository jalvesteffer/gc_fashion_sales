package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubcategoryDAO extends JpaRepository<Subcategory, Long> {
	
}
