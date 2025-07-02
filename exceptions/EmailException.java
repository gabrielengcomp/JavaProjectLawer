package exceptions;

public class EmailException extends Exception {
	
	private static final long serialVersionUID = -4867566541849738580L;

	public EmailException(String message) {
		super("Email Exception: " + message);
	}

}
