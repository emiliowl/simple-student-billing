package com.example.jmfc.billing;

import java.util.ArrayList;
import java.util.List;

import com.expert.jmfc.util.Book;
import com.expert.jmfc.util.BusinessException;

public class BillingFactory {
	
	private BillingFactory() {
		super();
	}

	private static List<Parcela> getPortionsFromStudentInfo(Book studentInfo) throws BusinessException {
		List<Parcela>portions = new ArrayList<Parcela>();
		int portionQuantity = studentInfo.get("QT-PARCELAS").getIntValue();
		for (int i = 1; i <= portionQuantity; i++) {
			int number = studentInfo.get("PARCELAS", i).get("NUMERO").getIntValue();
			double value = studentInfo.get("PARCELAS", i).get("VALOR").getDoubleValue();
			
			Parcela portion = new Parcela(number, value);
			portions.add(portion);
		}
			return portions;
	}
	
	public static Billing createBilling(Book studentInfo) throws BusinessException {
		return new Billing(
				studentInfo.get("STUDENT-ID").getIntValue(),
				studentInfo.get("STUDENT-NAME").get("FIRST-NAME").getValue().trim(),
				studentInfo.get("STUDENT-NAME").get("SURNAME").getValue().trim(),
				studentInfo.get("PARENTS-NAMES").get("FATHER-NAME").getValue().trim(),
				studentInfo.get("PARENTS-NAMES").get("MOTHER-NAME").getValue().trim(),
				getPortionsFromStudentInfo(studentInfo));
	}

}
