package com.smoothstack.gcfashion.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Transaction;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Long> {
}
