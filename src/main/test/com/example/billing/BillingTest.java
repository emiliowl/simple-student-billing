package com.example.billing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BillingTest {

	@Test
	public void shouldReceiveTheStringForBillingAndGenerateTheMailBodyFromIt() {
		Billing bill = new Billing("1234567JOAOZINHO SOBRENOME      JOSE      MARIA     001010300,00");
		assertEquals(1234567, bill.getStudentId());
		assertEquals("JOAOZINHO SOBRENOME", bill.getStudentName());
		assertEquals("JOSE", bill.getFatherName());
		assertEquals("MARIA", bill.getMotherName());
		assertEquals(1, bill.getQuantidadeParcelas());
		Parcela parcela = new Parcela(01, 300.00);
		List<Parcela> parcelas = new ArrayList<Parcela>();
		parcelas.add(parcela);
		assertArrayEquals(parcelas.toArray(), bill.getParcelas().toArray());
	}
	
	@Test
	public void shouldNotAcceptStringWithLessThan55Characteres() {
		try {
			new Billing("1234567JOAOZINHO SOBRENOME      JOSE      MARIA     00");
			fail("Should generate IllegalArgumentException!");
		} catch (IllegalArgumentException ex) {
			assertEquals("Comunicação inválida - String de entrada deve ter no mínimo 55 caracteres.", ex.getMessage());
		}
	}
	
	@Test
	public void shouldVerifyTheDataIntegrityForNextPortion() {
		try {
			new Billing("1234567JOAOZINHO SOBRENOME      JOSE      MARIA     001010400,0");
			fail("Should generate IllegalArgumentException!");
		} catch (IllegalArgumentException ex) {
			assertEquals("Comunicação inválida - Dados insuficientes para calcular próxima parcela", ex.getMessage());
		}
	}
	
}