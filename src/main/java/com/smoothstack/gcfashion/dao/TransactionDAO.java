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

	// For more complicated queries, we use native SQL queries
	
	
	// Returns all transactions with status "complete"
	@Query(value = "SELECT * FROM transaction t WHERE status='complete'", nativeQuery = true)
	List<Transaction> findAllCompleteTransactions();
	
	// Finds any open transcation for a given user id 
	@Query(value = "SELECT * FROM transaction t WHERE user_id=:userId AND status='open' LIMIT 1", nativeQuery = true)
	Optional<Transaction> findOpenTransactionsByUserId(@Param("userId") Long userId);
	
	// Searches complete transactions with a given value contained in its id
	@Query(value = "SELECT * FROM transaction t WHERE status='complete' AND t.transaction_id LIKE %:transactionId%", nativeQuery = true)
    List<Transaction> findCompleteLike(@Param("transactionId") Long transactionId);
}