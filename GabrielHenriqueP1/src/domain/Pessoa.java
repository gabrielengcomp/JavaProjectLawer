package domain;

import contracts.IPessoa;
import exceptions.PessoaException;

public abstract class Pessoa implements IPessoa {
	private String nome;
	private String email;
	private String telefone;

	public Pessoa(String nome, String email, String telefone) throws PessoaException {
		if (nome == null) {
			throw new PessoaException("Obrigatório fornecer nome");
		}

		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public abstract String getCadastroRF();
}
