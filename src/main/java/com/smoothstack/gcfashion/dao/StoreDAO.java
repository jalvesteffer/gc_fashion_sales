package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;
import com.smoothstack.gcfashion.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StoreDAO extends JpaRepository<Store, Long> {
	
}
