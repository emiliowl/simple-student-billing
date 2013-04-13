package com.example.billing;

import java.util.ArrayList;
import java.util.List;

public class Billing {
	
	private int studentId;
	private String firstName; //position 0-10
	private String surname; //position 10-25
	//reserved position for login (25-31)
	private String fatherName; //position 31-41
	private String motherName; //position 41-51
	private int quantidadeParcelas; //position 51-54
	private List<Parcela> parcelas; //position 54-56(number)/position 56-63(value)...63-65(number)/65-72(value)...
	
	/**
	 * Create a billing from an String entry, according to the following format:
	 * STUDENT-INFO.                                                         
	 * STUDENT-NAME.                                      
	 * 03 FIRST-NAME        PIC XXXXXXXXXX.               
	 * 03 SURNAME           PIC X(15).                  
	 * 03 LOGIN           	PIC X(6).  (WE DON'T USE IT)
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
		if (billingInfo.length() < 54)
			throw new IllegalArgumentException("Comunica‹o inv‡lida - String de entrada deve ter no m’nimo 54 caracteres.");
		setFirstName(billingInfo.substring(0, 10));
		setSurname(billingInfo.substring(10, 25));
		setFatherName(billingInfo.substring(31, 41));
		setMotherName(billingInfo.substring(41, 51));
		setQuantidadeParcelas(billingInfo.substring(51, 54));
		//calculando parcelas
		parcelas = new ArrayList<Parcela>();
		for (int i = 0, init=54; i < quantidadeParcelas; i++) {
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
