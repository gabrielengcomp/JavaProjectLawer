package exceptions;

public class DespesaException extends Exception {

	private static final long serialVersionUID = 2146065388664708256L;

	public DespesaException(String message) {
		super("Despesa Exception: " + message);
	}
}
