package exceptions;

public class TribunalException extends Exception {

	private static final long serialVersionUID = 6392584220927902287L;

	public TribunalException(String message) {
		super("Tribunal Exception: " + message);
	}
}
