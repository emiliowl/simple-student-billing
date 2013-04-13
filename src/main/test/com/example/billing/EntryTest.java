package com.example.billing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class EntryTest {

	@Test
	public void shouldParseEntryParameterAndSendItAsTheSTUDENT_ID() throws InterruptedException {
		try {
			Process proc = Runtime.getRuntime().exec("java -cp bin/ com.example.billing.Entry 1234567");
			proc.waitFor();
			String result = getProcessSysout(proc);
			
			assertTrue(result.contains("STUDENT-ID: 1234567"));
			assertTrue(result.contains("string sent: TRANC1   1234567USUARIO123SENHA123"));
		} catch (IOException e) {
			fail("something went wrong!");
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReceiveTheTranc1ContentAsResultForMFCall() throws InterruptedException {
		try {
			Process proc = Runtime.getRuntime().exec("java -cp bin/:/Users/emiliowl/.m2/repository/com/expert/mf-communicator-mock/0.0.1-SNAPSHOT/mf-communicator-mock-0.0.1-SNAPSHOT.jar com.example.billing.Entry 1234567");
			proc.waitFor();
			String result = getProcessSysout(proc);
			
			assertTrue(result.contains("string received: 1234567JOAOZINHO DA SILVA       JOSE      MARIA     00101+050000"));
		} catch (IOException e) {
			fail("something went wrong!");
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReceive123AndConvertTo0000123() throws InterruptedException {
		try {
			Process proc = Runtime.getRuntime().exec("java -cp bin/ com.example.billing.Entry 123");
			proc.waitFor();
			String result = getProcessSysout(proc);
			
			assertTrue(result.contains("STUDENT-ID: 123"));
			assertTrue(result.contains("string sent: TRANC1   0000123USUARIO123SENHA123"));
		} catch (IOException e) {
			fail("something went wrong!");
			e.printStackTrace();
		}
	}
	
	private String getProcessSysout(Process proc) throws IOException {
		InputStream is = proc.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		String result = "";
		char nextChar = Character.NON_SPACING_MARK;
		do {
			if (nextChar != Character.NON_SPACING_MARK);
			result += nextChar;
			nextChar = (char)bis.read();
		} while (nextChar != -1 && Character.isDefined(nextChar));
		return result;
	}
	
}
