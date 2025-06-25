package utils;

public class CnpjUtil {
	
	public static boolean validaCnpj(String cnpj) {
		
		if (cnpj == null || cnpj.isBlank() || cnpj.isEmpty()) {
			return false;
		}
		
		if (cnpj.length() != 14) {
			return false;
		}
		
		return true;
	}
}
