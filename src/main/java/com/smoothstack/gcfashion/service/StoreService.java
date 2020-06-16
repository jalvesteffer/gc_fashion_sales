package com.smoothstack.gcfashion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.gcfashion.dao.CouponDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;

import com.smoothstack.gcfashion.entity.Transaction;

/**
 * @author jalveste
 *
 */
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
	 * Get a transaction by id
	 * 
	 * @param transactionId transaction with Id to get
	 * @return Transaction with matching Id if it exists; if not, null is returned
	 */
	public Transaction findTransactionById(long transactionId) {

		// get transaction by id
		Optional<Transaction> optVal = tDAO.findById(transactionId);

		// return value if present; otherwise, null
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
				tDAO.save(transaction);
			} else {
				return -1;
			}
		}

		// deletion case when an id is given but no name
		else if (transaction.getTransactionId() != null) {

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
