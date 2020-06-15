package com.smoothstack.gcfashion.dao;
import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long>{

//	List<Transaction> findByIdTransactionId(Long transactionId);
}






