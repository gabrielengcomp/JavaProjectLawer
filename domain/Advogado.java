package domain;

import java.io.Serializable;

import contracts.IPessoa;
import exceptions.AdvogadoException;
import exceptions.PessoaException;

public class Advogado implements IPessoa, Serializable {
	private static final long serialVersionUID = -659491220717360631L;
	private final String registro;
	private final PessoaFisica pessoaFisica;

	public Advogado(PessoaFisica p, String registro) throws PessoaException, AdvogadoException {
		this.pessoaFisica = p;

		if (registro == null) {
			throw new AdvogadoException("Registro inválido ou ausente");
		}
		this.registro = registro;
	}

	public String getRegistro() {
		return registro;
	}

	public String getNome() {
		return pessoaFisica.getNome();
	}

	public String getEmail() {
		return pessoaFisica.getEmail();
	}

	public String getTelefone() {
		return pessoaFisica.getEmail();
	}

	public String getCadastroRF() {
		return pessoaFisica.getCadastroRF();
	}
}
