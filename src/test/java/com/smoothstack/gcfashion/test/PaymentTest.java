package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.smoothstack.gcfashion.dao.InventoryDAO;
import com.smoothstack.gcfashion.dao.ProductDAO;
import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.dao.UserDAO;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTest {

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
