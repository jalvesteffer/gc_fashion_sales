package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Create {
	
	@Mock
	TransactionDAO tDAO;
	
	@InjectMocks
	StoreService storeService;

	@Test
	public void testInvalidCreateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.save(transaction)).thenThrow(IllegalArgumentException.class);
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testValidCreateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 0);
	}
}