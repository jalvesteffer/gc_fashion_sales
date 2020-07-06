package com.smoothstack.gcfashion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/gcfashions/sales")
public class StoreController {

	@Autowired
	StoreService storeService;

	@GetMapping("/transactions/complete/like/{transactionId}")
	public ResponseEntity<List<Transaction>> getAllOpenTransactionsLike(@PathVariable Long transactionId) {

		// read all products
		List<Transaction> transactions = storeService.getAllCompleteTransactionsLike(transactionId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (transactions != null && transactions.size() > 0) {
			return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
		} else {
			// products not found, return 404 status
			return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
		}
	}

	@PutMapping("/transactions/refund")
	public ResponseEntity<Integer> refund(@RequestBody Map<String, Object> values) {

		Long transactionId = -1L;
		Transaction transaction = null;
		Integer retVal = -1;

		// get transactionId to refund
		if (!values.isEmpty() && values.get("transactionId") != null) {
			transactionId = ((Number) values.get("transactionId")).longValue();
		}

		// read transaction by Id
		if (transactionId != -1L) {
			transaction = storeService.findTransactionById(transactionId);
		}

		// refund payment with given paymentIntent id
		if (transaction != null && !transaction.getPaymentId().isEmpty()) {
			retVal = storeService.refundTransaction(transaction.getPaymentId());
		}

		// return request status
		if (retVal == 0) {
			storeService.updateTransactionStatus(transactionId, "refunded");
			return new ResponseEntity<Integer>(retVal, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/transactions/complete")
	public ResponseEntity<List<Transaction>> getAllCompleteTransactions() {

		List<Transaction> transactions = null;

		// get all open transactions
		transactions = storeService.findAllCompleteTransactions();

		// return all open transactions if there are any;
		// otherwise, return no content status
		if (transactions != null && transactions.size() > 0) {
			// return response
			return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);

		} else {
			// return response
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {

		// read transaction by Id passed in body
		Transaction transaction = storeService.findTransactionById(id);

		// a successful request should produce non-null transaction return value
		if (transaction != null) {
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/transactions/open/userid/{userId}")
	public ResponseEntity<Transaction> getOpenTransactionByUserId(@PathVariable Long userId) {

		Transaction transaction = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// set productList to null if no open transaction for userId was found
		// otherwise, create a product list representing users shopping cart
		// from that transaction info
		if (retVal == -1) {
			// return response
			return ResponseEntity.notFound().build();
		} else {
			transaction = storeService.findTransactionById(retVal);

			// return response
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		}
	}

	@GetMapping("/transactions/cart/userid/{userId}")
	public ResponseEntity<List<Product>> getCartByUserId(@PathVariable Long userId) {

		List<Product> productList = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// set productList to null if no open transaction for userId was found
		// otherwise, create a product list representing users shopping cart
		// from that transaction info
		if (retVal == -1) {
			productList = new ArrayList<>(); // create empty list
		} else {
			productList = storeService.getCompleteTransactionDetails(retVal);
		}

		// return response
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping("/transactions/open/coupon/userid/{userId}")
	public ResponseEntity<Coupon> getCouponByUserId(@PathVariable Long userId) {

		Coupon coupon = null;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// return 404 if no open transaction for userId was found
		// otherwise, return coupon associated with open transaction for user
		if (retVal == -1) {
			// return response
			return ResponseEntity.noContent().build();
		} else {
			coupon = storeService.getCoupon(retVal);

			// return response
			return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		}
	}

	@PostMapping("/transactions/open/coupon")
	public ResponseEntity<String> addCouponByUserId(@RequestBody Transaction t) {

		Transaction existingTransaction = null;
		List<Coupon> existingCoupons = null;
		Integer returnInt = -1; // for determining HttpStatus

		Long retVal = storeService.openTransactionsExist(t.getUserId());

		// an open transaction exists, update transaction
		if (retVal != -1) {
			// get a copy of the existing transaction
			existingTransaction = storeService.findTransactionById(retVal);

			// set new coupon values
			existingTransaction.setCoupons(t.getCoupons());

			// update the existing transaction
			returnInt = storeService.saveTransaction(existingTransaction);
		}

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			// get a copy of the existing transaction
			existingTransaction = storeService.findTransactionById(retVal);

			// set new coupon values
			existingTransaction.setCoupons(null);

			// update the existing transaction
			returnInt = storeService.saveTransaction(existingTransaction);

			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/transactions/open/userid/{userId}/sku/{sku}")
	public ResponseEntity<String> removeFromOpenTransactionByUserId(@PathVariable Long userId, @PathVariable Long sku) {

		Transaction transaction = null;
		int idxOfSku = -1;

		// get any open transaction by userId passed as PathVariable
		Long retVal = storeService.openTransactionsExist(userId);

		// if no open transaction for userId was found, return 404
		// otherwise, delete the item with sku from the transaction
		if (retVal == -1) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		} else {
			// get a copy of the transaction
			transaction = storeService.findTransactionById(retVal);

			// find the location in transaction's inventory list of matching sku
			for (idxOfSku = 0; idxOfSku < transaction.getInventory().size(); idxOfSku++) {
				if (sku == transaction.getInventory().get(idxOfSku).getSku()) {
					break;
				}
			}

			// remove the item with matching sku from inventory and update the transaction
			transaction.getInventory().remove(idxOfSku);
			storeService.saveTransaction(transaction);

			return new ResponseEntity<String>("", HttpStatus.OK);
		}

	}

	@PutMapping("/transactions")
	public ResponseEntity<String> updateTransaction(@RequestBody Transaction t) {

		Integer returnInt = -1; // for determining HttpStatus

		// update a transaction
		returnInt = storeService.saveTransaction(t);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/transactions")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction t) {

		Transaction existingTransaction = null;
		List<Inventory> existingInventory = null;
		Integer returnInt = -1; // for determining HttpStatus

		Long retVal = storeService.openTransactionsExist(t.getUserId());

		// no open transaction exists, create a new one
		if (retVal == -1) {
			returnInt = storeService.saveTransaction(t);
		}

		// an open transaction exists, update transaction
		else {
			// get a copy of the existing transaction
			existingTransaction = storeService.findTransactionById(retVal);

			// combine the new item in the transaction to the items from the existing
			// transaction
			existingInventory = existingTransaction.getInventory();
			existingInventory.addAll(t.getInventory());

			// set new transaction values
			t.setTransactionId(retVal);
			t.setInventory(existingInventory);

			// update the existing transaction
			returnInt = storeService.saveTransaction(t);
		}

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			// SKU already exists in cart. Ignore this error, so return OK
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
	}

	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {

		Integer returnInt = -1; // for determining HttpStatus

		Transaction t = new Transaction();
		t.setTransactionId(id);

		// update a transaction
		returnInt = storeService.saveTransaction(t);

		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/checkout")
	public ResponseEntity<Integer> updateTransactionCost(@RequestBody Map<String, Object> values) {

		Integer retVal = storeService.updateTransactionCost(values);

		// indicate success or failure
		if (retVal == 0) {
			return new ResponseEntity<Integer>(retVal, HttpStatus.OK);
		} else if (retVal == 1) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// Creates a new stripe payment intent and returns the client_secret to complete
	// the transaction
	@PostMapping("/checkout")
	public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody Transaction t) {
		Map<String, String> retVal = null;

		retVal = storeService.createPaymentIntent(t);

		// indicate success or failure
		if (retVal != null) {
			return new ResponseEntity<Map<String, String>>(retVal, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
