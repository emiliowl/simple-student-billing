package com.example.billing;

public class Parcela {

	private int number;
	private double value;
	
	/**
	 * Cria uma parcela (mensalidade escolar)
	 */
	public Parcela(int number, double value) {
		this.number = number;
		this.value = value;
	}
	
	public int getNumber() {
		return number;
	}
	
	public double getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Parcela))
			return false;
		Parcela another = (Parcela)arg0;
		return this.number == another.number;
	}

	/* (non-Javadoc)
	 * if the number of the parcela is the same, the 
	 */
	@Override
	public int hashCode() {
		return this.number;
	}
		
}