package utils;

public class CpfUtil {
	
	public static boolean validaCpf(String cpf) {
		
		if (cpf == null || cpf.isBlank() || cpf.isEmpty()) {
			return false;
		}
		
		if (cpf.length() != 11) {
			return false;
		}
		
		return true;
	}

}
