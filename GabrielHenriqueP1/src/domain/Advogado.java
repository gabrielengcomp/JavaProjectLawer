package domain;

import contracts.IPessoa;
import exceptions.AdvogadoException;
import exceptions.PessoaException;
import utils.RegistroUtil;

public class Advogado implements IPessoa {
	private final String registro;
	private final PessoaFisica pessoaFisica;

	public Advogado(PessoaFisica p, String registro) throws PessoaException, AdvogadoException {
		this.pessoaFisica = p;

		if (RegistroUtil.validaRegistro(registro)) {
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
