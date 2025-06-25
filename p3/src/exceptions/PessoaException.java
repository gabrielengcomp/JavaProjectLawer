package exceptions;

public class PessoaException extends Exception{
	
	private static final long serialVersionUID = -9072466729421804737L;
	
	public PessoaException(String message) {
		super("Pessoa Exception: " + message);
	}
}
