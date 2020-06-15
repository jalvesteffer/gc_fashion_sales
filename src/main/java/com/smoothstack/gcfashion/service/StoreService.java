package com.smoothstack.gcfashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.gcfashion.dao.CategoryDAO;
import com.smoothstack.gcfashion.dao.CouponDAO;
import com.smoothstack.gcfashion.dao.SubcategoryDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.StoreDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;

import com.smoothstack.gcfashion.entity.Category;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Subcategory;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.Store;
import com.smoothstack.gcfashion.entity.Product;

@Service
public class StoreService {

	@Autowired
	CategoryDAO cDAO;
	
	@Autowired
	CouponDAO cpDAO;
	
	@Autowired
	SubcategoryDAO scDAO;
	
	@Autowired
	StoreDAO sDAO;
	
	@Autowired
	ProductDAO pDAO;
	
	@Autowired
	TransactionDAO tDAO;
	
	/**
	 * Returns all categories
	 */
	public List<Category> findAllCategories() {
		
		return cDAO.findAll();
	};
	
	/**
	 * Returns all subcategories
	 */
	public List<Subcategory> findAllSubcategories() {
		
		return scDAO.findAll();
	};
	
	/**
	 * Returns all stores
	 */
	public List<Store> findAllStores() {
		
		return sDAO.findAll();
	};
	
	/**
	 * Returns all transactions
	 */
	public List<Transaction> findAllTransactions() {
		
		return tDAO.findAll();
	};
	
	/**
	 * Returns all coupons
	 */
	public List<Coupon> findAllCoupons() {
		return cpDAO.findAll();
	};

//	/**
//	 * Returns all coupons by catId
//	 */
//	public List<Coupon> findCouponsByCatId(long catId) {
//		return cpDAO.findByCatId(catId);
//	};
	
	/**
	 * Returns all products
	 */
	public List<Product> findAllProducts() {
		return pDAO.findAll();
	};

	/**
	 * Returns all products by catId
	 */
	public List<Product> findProductsByCatId(long catId) {
		return pDAO.findByCatId(catId);
	};
	
	/**
	 * Returns all products by catId
	 */
	public List<Product> findProductsByProductId(long productId) {
		return pDAO.findByProductId(productId);
	};
}
