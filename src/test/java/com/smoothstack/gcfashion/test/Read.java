package com.smoothstack.gcfashion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class Read {
	
	@Mock
	TransactionDAO tDAO;
	
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

}