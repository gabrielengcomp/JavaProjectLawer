package utils;

public class CpfUtil {

	public static boolean validaCpf(String cpf) {
		return cpf.matches("(\\d{3}\\.?){2}\\d{3}-?\\d{2}");
	}

}
