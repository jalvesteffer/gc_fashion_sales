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

import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Read {

	@Mock
	TransactionDAO tDAO;

	@InjectMocks
	StoreService storeService;

	@Test
	public void testFindAllTransactionsEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		when(tDAO.findAll()).thenReturn(transactions);

		assertEquals(storeService.findAllTransactions().size(), 0);

	}

	@Test
	public void testFindAllTransactionsNotEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();

		transactions.add(t1);
		transactions.add(t2);

		when(tDAO.findAll()).thenReturn(transactions);

		assertEquals(storeService.findAllTransactions().size(), 2);

	}

	@Test
	public void testFindAllCompleteTransactionsEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		when(tDAO.findAllCompleteTransactions()).thenReturn(transactions);

		assertEquals(storeService.findAllCompleteTransactions().size(), 0);

	}

	@Test
	public void testFindAllCompleteTransactionsNotEmpty() {
		List<Transaction> transactions = new ArrayList<>();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();

		t1.setStatus("complete");
		t2.setStatus("complete");

		transactions.add(t1);
		transactions.add(t2);

		when(tDAO.findAllCompleteTransactions()).thenReturn(transactions);

		assertEquals(storeService.findAllCompleteTransactions().size(), 2);

	}

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
	public void testgetCompleteTransactionDetailsEmpty() {
		
		when(tDAO.findOpenTransactionsByUserId(10L)).thenReturn(Optional.empty());
		
		long retVal = storeService.openTransactionsExist(10L); 
		
		assertEquals(retVal, -1L);

	}
}