package com.example.jmfc.billing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mfmock.ImsConnectMock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.expert.jmfc.parser.MainframeParser;
import com.expert.jmfc.store.BookStore;
import com.expert.jmfc.util.BusinessException;

public class BillingFactoryTest {
	
	@Test
	public void shouldGenerateBillingFromBook() throws BusinessException {
		Billing billing = BillingFactory.createBilling(1234567, BookStore.getBook("tranc1-out").get("STUDENT-INFO"));
		assertEquals("JOAOZINHO DA SILVA", billing.getFullName());
		assertEquals("JOSE", billing.getFatherName());
		assertEquals("MARIA", billing.getMotherName());
		assertEquals(1, billing.getQuantidadeParcelas());
		assertEquals(1, billing.getParcelas().get(0).getNumber());
		assertEquals(500.00, billing.getParcelas().get(0).getValue(), 0.00);
	}
	
	@BeforeClass
	public static void loadBooksInMemory() throws IOException, BusinessException {
		Map<String, String> books = new HashMap<String, String>();
		books.put("tranc1-in", Entry.class.getClassLoader().getResource("TRANC1-IN.book").getPath());
		books.put("tranc1-out", Entry.class.getClassLoader().getResource("TRANC1-OUT.book").getPath());
		BookStore.loadBooks(books);
	}
	
	@Before
	public void loadBookDataInMemory() throws IOException, BusinessException {
		//set the values for call mainframe
		BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("STUDENT-ID").setJavaValue("1234567");
		BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("CREDENTIALS").get("USER").setJavaValue("USUARIO123");
		BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("CREDENTIALS").get("PASSWORD").setJavaValue("SENHA123");
		//call the mainframe connector with the string:
		String input = "TRANC1   ".concat(MainframeParser.parseBookToString(BookStore.getBook("tranc1-in")));
		String output = ImsConnectMock.getConnection().call(input);
		MainframeParser.populateBook(output, BookStore.getBook("tranc1-out"));
	}
	
}
