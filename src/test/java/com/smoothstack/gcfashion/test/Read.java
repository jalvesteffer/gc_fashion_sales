package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Read {

	@Mock
	TransactionDAO tDAO;
	
	@Mock
	ProductDAO pDAO;

	@InjectMocks
	StoreService storeService;

	@Test
	public void testGetInvalidTransaction() {

		Optional<Transaction> optVal = Optional.empty();

		when(tDAO.findById(-1L)).thenReturn(optVal);
		when(tDAO.findById(0L)).thenReturn(optVal);
		when(tDAO.findById(1000L)).thenReturn(optVal);

		assertNull(storeService.findTransactionById(-1L));
		assertNull(storeService.findTransactionById(0L));
		assertNull(storeService.findTransactionById(1000L));

	}

	@Test
	public void testGetValidTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);

		when(tDAO.findById(1L)).thenReturn(Optional.of(transaction));

		assertEquals(storeService.findTransactionById(1L), transaction);

	}
	
	@Test
	public void testGetCouponEmpty() {

		when(tDAO.findById(1L)).thenReturn(Optional.empty());

		assertNull(storeService.getCoupon(1L));

	}
	
	@Test
	public void testGetCouponNone() {
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		transaction.setCoupons(null);

		when(tDAO.findById(1L)).thenReturn(Optional.of(transaction));

		assertNull(storeService.getCoupon(1L));

	}
	
	@Test
	public void testGetCouponNotEmpty() {
		
		Transaction transaction = new Transaction();
		Coupon coupon = new Coupon();
		coupon.setCouponId(10L);
		List<Coupon> coupons = new ArrayList<>();
		coupons.add(coupon);
		
		transaction.setTransactionId(1L);
		transaction.setCoupons(coupons);

		when(tDAO.findById(1L)).thenReturn(Optional.of(transaction));

		assertEquals(storeService.getCoupon(1L), coupon);

	}

	@Test
	public void testOpenTransactionsExistBadId() {
		
		when(tDAO.findOpenTransactionsByUserId(10L)).thenReturn(Optional.empty());
		
		long retVal = storeService.openTransactionsExist(10L); 
		
		assertEquals(retVal, -1L);

	}
	
	@Test
	public void testOpenTransactionsExistValidId() {
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(30L);
		
		when(tDAO.findOpenTransactionsByUserId(10L)).thenReturn(Optional.of(transaction));
		
		long retVal = storeService.openTransactionsExist(10L);
		
		assertEquals(retVal, 30L);

	}
	
	@Test
	public void testGetCompleteTransactionDetailsBadId() {
		
		List<Product> productList = new ArrayList<>();
		
		when(tDAO.findById(10L)).thenReturn(Optional.empty());
		
		assertNull(storeService.getCompleteTransactionDetails(10L));

	}
	
	@Test
	public void testGetCompleteTransactionDetailsEmptyInventoryList() {
		
		List<Product> productList = new ArrayList<>();
		List<Inventory> inventoryList = new ArrayList<>();
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setInventory(inventoryList);
		
		when(tDAO.findById(10L)).thenReturn(Optional.of(transaction));
		
		assertEquals(storeService.getCompleteTransactionDetails(10L), productList);

	}
	
	@Test
	public void testGetCompleteTransactionDetailsValid() {
		
		List<Product> productListA = new ArrayList<>();
		List<Product> productListB = new ArrayList<>();
		List<Inventory> inventoryList = new ArrayList<>();

		Inventory inventoryA =new Inventory();
		inventoryA.setProductId(1L);
		inventoryA.setQty(1L);
		Inventory inventoryB =new Inventory();
		inventoryB.setProductId(2L);
		inventoryB.setQty(1L);
		inventoryList.add(inventoryA);
		inventoryList.add(inventoryB);
		
		Product productOne = new Product();
		productOne.setProductId(1L);
		productOne.setProductName("Product 1");
		productOne.setPhoto("1.jpg");
		productOne.setPrice(10.00);
		productListA.add(productOne);
		
		Product productTwo = new Product();
		productOne.setProductId(2L);
		productOne.setProductName("Product 2");
		productOne.setPhoto("2.jpg");
		productOne.setPrice(20.00);
		productListB.add(productTwo);

		
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setInventory(inventoryList);
		
		when(tDAO.findById(10L)).thenReturn(Optional.of(transaction));
		when(pDAO.findByProductId(1L)).thenReturn(productListA);
		when(pDAO.findByProductId(2L)).thenReturn(productListB);
		
		assertEquals(storeService.getCompleteTransactionDetails(10L).size(), 2);

	}
	
	@Test
	public void testGetAllCompleteTransactionsLikeNoMatch() {
		List<Transaction> transactions = new ArrayList<>();
		
		when(tDAO.findCompleteLike(1L)).thenReturn(transactions);
		
		assertEquals(storeService.getAllCompleteTransactionsLike(1L), transactions);
	}
	
	@Test
	public void testGetAllCompleteTransactionsLikeMatch() {
		List<Transaction> transactions = new ArrayList<>();
		Transaction t = new Transaction();
		transactions.add(t);
		
		when(tDAO.findCompleteLike(1L)).thenReturn(transactions);
		
		assertEquals(storeService.getAllCompleteTransactionsLike(1L), transactions);
	}
}