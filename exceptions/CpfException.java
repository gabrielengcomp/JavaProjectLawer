package exceptions;

public class CpfException extends Exception {

	private static final long serialVersionUID = -6942782126495510715L;

	public CpfException(String message) {
		super("Cpf Exception: " + message);
	}
}
