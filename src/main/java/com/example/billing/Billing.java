package com.example.billing;

import java.util.ArrayList;
import java.util.List;

public class Billing {
	
	private int studentId; //position 0-7
	private String firstName; //position 7-17
	private String surname; //position 17-32
	private String fatherName; //position 32-42
	private String motherName; //position 42-52
	private int quantidadeParcelas; //position 52-55
	private List<Parcela> parcelas; //position 55-57(number)/position 57-64(value)...64-66(number)/66-73(value)...
	
	/**
	 * Create a billing from an String entry, according to the following format:
	 * STUDENT-INFO.                                      
	 * STUDENT-ID        	PIC 9(7).                      
	 * STUDENT-NAME.                                      
	 * 03 FIRST-NAME        PIC XXXXXXXXXX.               
	 * 03 SURNAME           PIC X(15).                    
	 * PARENTS-NAMES.                                     
	 * 03 FATHER-NAME    PIC XXXXXXXXXX.                  
	 * 03 MOTHER-NAME    PIC XXXXXXXXXX.                  
	 * DETALHES DAS PARCELAS DO ESTUDANTE                 
	 * QT-PARCELAS             PIC 999.                   
	 * PARCELAS OCCURS 1 TIMES DEPENDING ON QT-PARCELAS.                          
	 * 03 NUMERO               PIC 99.                    
	 * 03 VALOR                PIC +9999V99.              
	 * @param billingInfo
	 */
	public Billing(String billingInfo) {
		if (billingInfo.length() < 55)
			throw new IllegalArgumentException("Comunica‹o inv‡lida - String de entrada deve ter no m’nimo 55 caracteres.");
		setStudentId(billingInfo.substring(0, 7));
		setFirstName(billingInfo.substring(7, 17));
		setSurname(billingInfo.substring(17, 32));
		setFatherName(billingInfo.substring(32, 42));
		setMotherName(billingInfo.substring(42, 52));
		setQuantidadeParcelas(billingInfo.substring(52, 55));
		//calculando parcelas
		parcelas = new ArrayList<Parcela>();
		for (int i = 0, init=55; i < quantidadeParcelas; i++) {
			if (billingInfo.length() < init + 9)
				throw new IllegalArgumentException("Comunica‹o inv‡lida - Dados insuficientes para calcular pr—xima parcela");
			int number = Integer.parseInt(billingInfo.substring(init, init += 2));
			double value = new Double(billingInfo.substring(init, init += 7).replace(',', '.'));
			Parcela parcela = new Parcela(number, value);
			parcelas.add(parcela);
		}
	}
	
	private void setStudentId(String studentId) {
		this.studentId = Integer.parseInt(studentId);
	}
	
	private void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}
	
	private void setSurname(String surname) {
		this.surname = surname.trim();
	}
	
	private void setFatherName(String fatherName) {
		this.fatherName = fatherName.trim();
	}
	
	private void setMotherName(String motherName) {
		this.motherName = motherName.trim();
	}
	
	private void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = Integer.parseInt(quantidadeParcelas);
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @return the quantidadeParcelas
	 */
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	
	/**
	 * @return the parcelas
	 */
	public List<Parcela> getParcelas() {
		return parcelas;
	}

	/**
	 * @return the complete student name
	 */
	public String getStudentName() {
		return firstName + " " + surname;
	}
}
