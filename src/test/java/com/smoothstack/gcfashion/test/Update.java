package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
public class Update {
	
	@Mock
	TransactionDAO tDAO;
	
	@InjectMocks
	StoreService storeService;

	@Test
	public void testInvalidUpdateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testValidUpdateTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 0);
	}
}