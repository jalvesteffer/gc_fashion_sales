package com.smoothstack.gcfashion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.service.StoreService;

@RestController
@RequestMapping("/gcfashions")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		// read all categories
		List<Category> categories = storeService.findAllCategories();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (categories != null && categories.size() > 0) {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/subcategories")
	public ResponseEntity<List<Subcategory>> getAllSubcategory() {
		
		// read all subcategories
		List<Subcategory> subcategories = storeService.findAllSubcategories();
		System.out.println("Number of subcategories read: " + subcategories.size());
		// a successful request should produce a list not null with a size greater than
		// zero
		if (subcategories != null && subcategories.size() > 0) {
			return new ResponseEntity<List<Subcategory>>(subcategories, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/stores")
	public ResponseEntity<List<Store>> getAllStores() {
		
		// read all stores
		List<Store> stores = storeService.findAllStores();
		System.out.println("Number of subcategories read: " + stores.size());
		// a successful request should produce a list not null with a size greater than
		// zero
		if (stores != null && stores.size() > 0) {
			return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		
		// read all stores
		List<Transaction> transactions = storeService.findAllTransactions();
		// a successful request should produce a list not null with a size greater than
		// zero
		if (transactions != null && transactions.size() > 0) {
			return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
		} else {
			// author id not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("shop/coupons")
	public ResponseEntity<List<Coupon>> findAllCoupons() {
		
		// read Coupons
		List<Coupon> coupons = storeService.findAllCoupons();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (coupons  != null && coupons.size() > 0) {
			return new ResponseEntity<List<Coupon>>(coupons , HttpStatus.OK);
		} else {
			// Coupons  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	};
	
	@GetMapping("shop/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		
		// read all products
		List<Product> products = storeService.findAllProducts();

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("shop/products/{productId}")
	public ResponseEntity<List<Product>> getProductByProductId(@PathVariable long productId) {
		
		// read products
		List<Product> products = storeService.findProductsByProductId(productId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	};
	
	@GetMapping("/shop/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCatId(@PathVariable long catId) {
		
		// read products
		List<Product> products = storeService.findProductsByCatId(catId);

		// a successful request should produce a list not null with a size greater than
		// zero
		if (products  != null && products.size() > 0) {
			return new ResponseEntity<List<Product>>(products , HttpStatus.OK);
		} else {
			// products  not found, return 404 status
			return ResponseEntity.notFound().build();
		}
	};
}
