package com.example.billing;

/**
 * Fake mail sender
 * @author emiliowl
 *
 */
public class MailSender {
	public static void sendMail(Object billingToSend) {
		System.out.println("e-mail dispatched!");
		System.out.println(billingToSend.toString());
	}
}
