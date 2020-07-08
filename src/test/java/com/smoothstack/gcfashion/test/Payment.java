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

import com.smoothstack.gcfashion.dao.InventoryDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.UserDAO;
import com.smoothstack.gcfashion.entity.Coupon;
import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Payment {

	@Mock
	TransactionDAO tDAO;
	
	@Mock
	UserDAO uDAO;
	
	@Mock
	ProductDAO pDAO;
	
	@Mock
	InventoryDAO iDAO;
	
	@InjectMocks
	StoreService storeService;
	
	@Test
	public void testCreatePaymentIntentNull() {
		Transaction t = new Transaction();

		assertNull(storeService.createPaymentIntent(t));
	}
	
	@Test
	public void testRefundTransactionNull() {
		int retVal = storeService.refundTransaction(null);
		
		assertEquals(retVal, 1);
	}
}
