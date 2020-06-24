package com.smoothstack.gcfashion.dao;
import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long>{

	@Query(value = "SELECT * FROM transaction t WHERE user_id=:userId AND status='open' LIMIT 1", nativeQuery = true)
	Optional<Transaction> findOpenTransactionsByUserId(@Param("userId") Long userId);
}






