package exceptions;

public class AudienciaException extends Exception{
	
	private static final long serialVersionUID = 4438634441935419134L;

	public AudienciaException(String message) {
		super("Audiencia Exception: " + message);
	}
}
