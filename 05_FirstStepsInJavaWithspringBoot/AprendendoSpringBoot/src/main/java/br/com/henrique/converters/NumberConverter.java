package br.com.henrique.converters;

public class NumberConverter {

	public static Double convertToDouble(String StrNumber) {
		if (StrNumber == null) return 0D;
		String number = StrNumber.replaceAll(",",".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isNumeric(String StrNumber) {
		if (StrNumber == null) return false;
		String number = StrNumber.replaceAll(",",".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
