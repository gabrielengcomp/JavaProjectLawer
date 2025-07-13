package domain;

import exceptions.PessoaException;
import shared.Cpf;
import shared.Email;

public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 4215889634153787065L;
	private final Cpf cpf;

	public PessoaFisica(String nome, Email email, String telefone, Cpf cpf) throws PessoaException {
		super(nome, email, telefone);
		this.cpf = cpf;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public String getCadastroRF() {
		return cpf.getValue();
	}

	public String getNome() {
		return super.getNome();
	}

	public String getEmail() {
		return super.getEmail();
	}

	public String getTelefone() {
		return super.getTelefone();
	}
}
