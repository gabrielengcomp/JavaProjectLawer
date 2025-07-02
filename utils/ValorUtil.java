package utils;

public class ValorUtil {

	public static boolean validaValor(double valor) {

		if (valor < 0) {
			return false;
		}

		return true;
	}
}
