package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.smoothstack.gcfashion.dao.TransactionDAO;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class SaveTransactionTest {
	
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
	public void testInvalidUpdateTransactionException() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(10L);
		transaction.setStoreId(1L);
		transaction.setStatus("open");
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		when(tDAO.save(transaction)).thenThrow(RuntimeException.class);
		
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
	
	@Test
	public void testInvalidDeleteTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1000L);
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 1);
	}
	
	@Test
	public void testInvalidDeleteTransactionException() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		doThrow(RuntimeException.class).when(tDAO).deleteById(1L);

		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, -1);
	}
	
	@Test
	public void testValidDeleteTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(1L);
		
		when(tDAO.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));
		
		int retVal = storeService.saveTransaction(transaction);
		
		assertEquals(retVal, 0);
	}
	
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