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
import com.smoothstack.gcfashion.entity.Inventory;
import com.smoothstack.gcfashion.entity.Product;
import com.smoothstack.gcfashion.entity.Transaction;
import com.smoothstack.gcfashion.entity.User;
import com.smoothstack.gcfashion.service.StoreService;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class Find {
	
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
	public void testFindUserByIdInvalid() {

		when(uDAO.findById(1L)).thenReturn(Optional.empty());

		assertNull(storeService.findUserById(1L));
	}
	
	@Test
	public void testFindUserByIdValid() {
		User user = new User();
		user.setUserId(1L);
		
		when(uDAO.findById(1L)).thenReturn(Optional.of(user));

		assertEquals(storeService.findUserById(1L), user);
	}
	
	@Test
	public void testFindProductByIdInvalid() {

		when(pDAO.findById(1L)).thenReturn(Optional.empty());

		assertNull(storeService.findProductById(1L));
	}
	
	@Test
	public void testFindProductByIdValid() {
		Product product = new Product();
		product.setProductId(1L);
		
		when(pDAO.findById(1L)).thenReturn(Optional.of(product));

		assertEquals(storeService.findProductById(1L), product);
	}
	
	@Test
	public void testFindInventoryByIdInvalid() {

		when(iDAO.findById(1L)).thenReturn(Optional.empty());

		assertNull(storeService.findInventoryById(1L));
	}
	
	@Test
	public void testFindInventoryByIdValid() {
		Inventory inventory = new Inventory();
		inventory.setProductId(1L);
		
		when(iDAO.findById(1L)).thenReturn(Optional.of(inventory));

		assertEquals(storeService.findInventoryById(1L), inventory);
	}
}