package com.example.jmfc.billing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mfmock.ImsConnectMock;

import com.example.billing.MailSender;
import com.expert.jmfc.parser.MainframeParser;
import com.expert.jmfc.store.BookStore;
import com.expert.jmfc.util.BusinessException;

public class Entry {
	public static void main(String[] args) {
		if (args.length != 1)
				throw new IllegalArgumentException("Expecting only 1 parameter.");
		System.out.println("STUDENT-ID: " + args[0]); //printing the student-id received for test purpose only
		
		//call jmfc for create the trancode
		try {
			init();
			//set the values for call mainframe
			BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("STUDENT-ID").setJavaValue(args[0]);
			BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("CREDENTIALS").get("USER").setJavaValue("USUARIO123");
			BookStore.getBook("tranc1-in").get("STUDENT-INFO").get("CREDENTIALS").get("PASSWORD").setJavaValue("SENHA123");
			
			//call the mainframe connector with the string:
			String input = "TRANC1   ".concat(MainframeParser.parseBookToString(BookStore.getBook("tranc1-in")));
			
			System.out.println("string sent: " + input); //printing the input string for test purpose only
			String output = ImsConnectMock.getConnection().call(input);
			System.out.println("string received: " + output); //printing the output string for test purpose only.
			
			MainframeParser.populateBook(output, BookStore.getBook("tranc1-out"));
									
			Billing billing = BillingFactory.createBilling(BookStore.getBook("tranc1-out").get("STUDENT-INFO"));
			MailSender.sendMail(billing);
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
		 	e.printStackTrace();
		}
	}
	
	/**
	 * Create the books in memory for the tranc1
	 * @throws IOException
	 * @throws BusinessException
	 */
	private static void init() throws IOException, BusinessException {
		Map<String, String> books = new HashMap<String, String>();
		books.put("tranc1-in", Entry.class.getClassLoader().getResource("TRANC1-IN.book").getPath());
		books.put("tranc1-out", Entry.class.getClassLoader().getResource("TRANC1-OUT.book").getPath());
		BookStore.loadBooks(books);
	}
	
}