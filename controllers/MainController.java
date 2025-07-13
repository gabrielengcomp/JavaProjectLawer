package controllers;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 */
public class MainController implements Serializable {

	private static final long serialVersionUID = 3326741752317644589L;

	private static MainController instance;
	
	private TribunalController tribunalController;
	private PessoaController pessoaController;
	private ProcessoController processoController;


	// declarar os demais controladores

	
	private MainController() {
		
		tribunalController = new TribunalController();
		pessoaController = new PessoaController();
		processoController = new ProcessoController(this.tribunalController, this.pessoaController);

		// instanciar os demais controladores
		
	}

	public static MainController getInstance() {
		return instance;
	}

	
	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}
	public static PessoaController getPessoaController() {
		return instance.pessoaController;
	}
	public static ProcessoController getProcessoController() {
		return instance.processoController;
	}
	
	// implementar metodos acessadores estaticos para os demais controladores
	
	
	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}
}