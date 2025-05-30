package utils;

public class RegistroUtil {
	
	public static boolean validaRegistro(String registro) {
		
		if (registro == null || registro.isBlank() || registro.isEmpty()) {
			return false;
		}
			
		return true;
	}
}
