package com.smoothstack.gcfashion.controller;

import java.util.List;

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

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/gcfashions/sales")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	

	
	
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
	
	@PutMapping("/transactions")
	public ResponseEntity<String> updateTransaction(@RequestBody Transaction t) {
		
		Integer returnInt = -1; // for determining HttpStatus
		
		// update a transaction
		returnInt = storeService.saveTransaction(t);
		
		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction t) {
		
		System.out.println("inside post method in controller");
		
		Integer returnInt = -1; // for determining HttpStatus
		
		// update a transaction
		returnInt = storeService.saveTransaction(t);
		
		// indicate success or failure
		if (returnInt == 0) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
	
}
