package domain;

import java.io.Serializable;

import contracts.IPessoa;
import exceptions.PessoaException;
import shared.Email;

public abstract class Pessoa implements IPessoa, Serializable {
	
	private static final long serialVersionUID = 3294270080309348911L;
	private String nome;
	private Email email;
	private String telefone;

	public Pessoa(String nome, Email email, String telefone) throws PessoaException{
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
		return email.getValue();
	}

	public void setEmail(Email email) {
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
