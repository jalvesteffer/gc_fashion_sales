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
public class Delete {
	
	@Mock
	TransactionDAO tDAO;
	
	@InjectMocks
	StoreService storeService;

	@Test
	public void testInvalidDeleteTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1000L);
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 1);
	}
	
	@Test
	public void testValidDeleteTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 0);
	}
}