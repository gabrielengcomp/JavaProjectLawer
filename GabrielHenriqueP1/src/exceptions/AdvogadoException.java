package exceptions;

public class AdvogadoException extends Exception{
	
	private static final long serialVersionUID = 5588098406418915207L;

	public AdvogadoException(String message) {
		super("AdvogadoException: " + message);
	}
}
