package com.example.billing;

import mfmock.ImsConnectMock;

public class Entry {
	public static void main(String[] args) {
		if (args.length != 1)
				throw new IllegalArgumentException("Expecting only 1 parameter.");
		System.out.println("STUDENT-ID: " + args[0]); //printing the student-id received for test purpose only
		
		//call the mainframe connector with the string:
		String input = "TRANC1   " + clean(args[0]) +  "USUARIO123SENHA123";
		System.out.println("string sent: " + input); //printing the input string for test purpose only
		String output = ImsConnectMock.getConnection().call(input);
		System.out.println("string received: " + output); //printing the output string for test purpose only
		MailSender.sendMail(new Billing(output));
	}
	
	/**
	 * Give the studentId necessary treatment for studentId (numérico de 7 posições)
	 * @param studentId
	 * @return
	 */
	private static String clean(String studentId) {
		int positionCut = 7;
		if(studentId.length() < 7)
			positionCut = studentId.length();
		
		String newString = studentId.substring(0, positionCut);
		
		return fillWithLeftZeros(Integer.parseInt(newString) + "", 7);
	}
	
	/**
	 * Fill some number with the number of necessary zeros to fit the desired number size
	 * @param number
	 * @param numberSize
	 * @return
	 */
	private static String fillWithLeftZeros(String number, int numberSize) {
		String _number = number;
		while (_number.length() < numberSize) {
			_number = "0" + _number;
		}
		return _number;
	}
}