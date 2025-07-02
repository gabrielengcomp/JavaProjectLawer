package exceptions;

public class CnpjException extends Exception {
	
	private static final long serialVersionUID = 5605917622019685073L;

	public CnpjException(String message) {
		super("Cnpj Exception: " + message);
	}
}
