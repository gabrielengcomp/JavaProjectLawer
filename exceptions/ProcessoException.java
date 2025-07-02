package exceptions;

public class ProcessoException extends Exception{

	private static final long serialVersionUID = -1443402928005159207L;
	
	public ProcessoException(String message) {
		super("Processo Exception: " + message);
	}
}
