package domain;

import exceptions.PessoaException;
import utils.CpfUtil;

public class PessoaFisica extends Pessoa{
	private final String cpf;

	public PessoaFisica(String nome, String email, String telefone, String cpf) throws PessoaException{
		super(nome, email, telefone);
		if (CpfUtil.validaCpf(cpf)) {
			throw new PessoaException("CPF inválido ou ausente");
		}
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getCadastroRF() {
		return getCpf();  
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

