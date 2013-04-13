package com.example.jmfc.billing;

import java.util.List;

public class Billing {
	
	private int studentId; 
	private String firstName;
	private String surname; 
	private String fatherName;
	private String motherName;
	private int quantidadeParcelas;
	private List<Parcela> parcelas;
	
	/**
	 * Create a Billing POJO             
	 * @param billingInfo
	 */
	public Billing(int studentId, String firstName, String surname, String fatherName, String motherName, List<Parcela> parcelas) {
		setStudentId(studentId);
		setFirstName(firstName);
		setSurname(surname);
		setFatherName(fatherName);
		setMotherName(motherName);
		setQuantidadeParcelas(parcelas.size());
		setParcelas(parcelas);
	}
	
	/**
	 * return the firstName more the surname
	 */
	public String getFullName() {
		return getFirstName().concat(" ").concat(getSurname());
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @return the quantidadeParcelas
	 */
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	/**
	 * @param quantidadeParcelas the quantidadeParcelas to set
	 */
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	/**
	 * @return the parcelas
	 */
	public List<Parcela> getParcelas() {
		return parcelas;
	}

	/**
	 * @param parcelas the parcelas to set
	 */
	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
	
}