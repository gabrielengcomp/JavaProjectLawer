package domain;

import exceptions.PessoaException;
import shared.Cnpj;
import shared.Email;

public class PessoaJuridica extends Pessoa{
	
	private static final long serialVersionUID = -7280124867721028634L;
	private final Cnpj cnpj;
	private PessoaFisica preposto;

	public PessoaJuridica(String nome, Email email, String telefone, Cnpj cnpj, PessoaFisica preposto) throws PessoaException{
		super(nome, email, telefone);
	
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public PessoaFisica getPreposto() {
		return preposto;
	}

	public void setPreposto(PessoaFisica preposto) {
		this.preposto = preposto;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}
	
	public String getCadastroRF() {
		return cnpj.getCnpj();  
	}
	
	public String getNome() {
		return super.getNome();
	}

}
