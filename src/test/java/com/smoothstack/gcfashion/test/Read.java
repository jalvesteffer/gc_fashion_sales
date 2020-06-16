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
	public void testGetAllEmptyLists() {
		Optional<Transaction> optVal = Optional.empty();
		when(tDAO.findById(100L)).thenReturn(optVal);
		assertNull(storeService.findTransactionById(100L));

	}

//	@Test
//	public void testGetAllAuthors() {
//		List<Author> aList = new ArrayList<>();
//		Author author1 = new Author();
//		author1.setAuthorId(1L);
//		author1.setAuthorName("Neil G");
//
//		Author author2 = new Author();
//		author2.setAuthorId(2L);
//		author2.setAuthorName("Bob B");
//
//		aList.add(author1);
//		aList.add(author2);
//
//		when(aDAO.findAll()).thenReturn(aList);
//		assertEquals(adminService.findAllAuthors().size(), 2);
//	}
//
//	@Test
//	public void testGetAllBooks() {
//		List<Book> bList = new ArrayList<>();
//		Book book1 = new Book();
//		book1.setBookId(1L);
//		book1.setTitle("Title 1");
//
//		Book book2 = new Book();
//		book2.setBookId(2L);
//		book2.setTitle("Title 2");
//
//		bList.add(book1);
//		bList.add(book2);
//
//		when(bDAO.findAll()).thenReturn(bList);
//		assertEquals(adminService.findAllBooks().size(), 2);
//	}
//
//	@Test
//	public void testGetAllPublishers() {
//		List<Publisher> pList = new ArrayList<>();
//		Publisher publisher1 = new Publisher();
//		publisher1.setPublisherId(1L);
//		publisher1.setPublisherName("Pub1");
//
//		Publisher publisher2 = new Publisher();
//		publisher2.setPublisherId(2L);
//		publisher2.setPublisherName("Pub2");
//
//		pList.add(publisher1);
//		pList.add(publisher2);
//
//		when(pDAO.findAll()).thenReturn(pList);
//		assertEquals(adminService.findAllPublishers().size(), 2);
//	}


}







