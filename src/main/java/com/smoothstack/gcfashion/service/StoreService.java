package com.smoothstack.gcfashion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.gcfashion.dao.CategoryDAO;
import com.smoothstack.gcfashion.dao.CouponDAO;
import com.smoothstack.gcfashion.dao.SubcategoryDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.UserDAO;
import com.smoothstack.gcfashion.dao.StoreDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Product;

@Service
public class StoreService {
	
	@Autowired
	CouponDAO cpDAO;
	
	@Autowired
	TransactionDAO tDAO;
	
	
	/**
	 * Returns all transactions
	 */
	public List<Transaction> findAllTransactions() {
		
		return tDAO.findAll();
	};
	
	/**
	 * Returns all transactions
	 */
	public Transaction findTransactionById(long transactionId) {
		
		Optional<Transaction> optVal = tDAO.findById(transactionId);
		if (optVal.isPresent()) {
			return optVal.get();
		} else {
			return null;
		}
	};
	
	
	
	public Integer saveTransaction(Transaction transaction) {

		// perform write operation depending on which object variables are set
		// update case where both a key and store id are given
		if (transaction.getTransactionId() != null && transaction.getStoreId() != null) {

			// update transaction if transaction id matches existing record
			if (tDAO.findById(transaction.getTransactionId()).isPresent()) {
				System.out.println("Transaction id found");
				tDAO.save(transaction);
			} else {
				System.out.println("Transaction id not found");
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (transaction.getTransactionId() != null) {
//
			// if author to delete doesn't exist, return error status
			if (tDAO.findById(transaction.getTransactionId()).isPresent()) {
				try {
					tDAO.deleteById(transaction.getTransactionId());
				} catch (Exception e) {
					// query error
					return -1;
				}
			} else {
				// not found
				return 1;
			}
		}

		// insertion case otherwise
		else {
			try {
				// create the new record
				tDAO.save(transaction);
			} catch (Exception e) {
				// query error
				return -1;
			}
		}
		

		return 0;
	}
		
}
