package com.smoothstack.gcfashion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.Inventory;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Long> {

}
