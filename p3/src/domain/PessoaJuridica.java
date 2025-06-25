package domain;

import exceptions.PessoaException;
import utils.CnpjUtil;

public class PessoaJuridica extends Pessoa{
	private final String cnpj;
	private PessoaFisica preposto;

	public PessoaJuridica(String nome, String email, String telefone, String cnpj, PessoaFisica preposto) throws PessoaException{
		super(nome, email, telefone);
		if (CnpjUtil.validaCnpj(cnpj)) {
			throw new PessoaException("CNPJ inválido ou ausente");
		}
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public PessoaFisica getPreposto() {
		return preposto;
	}

	public void setPreposto(PessoaFisica preposto) {
		this.preposto = preposto;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public String getCadastroRF() {
		return getCnpj();  
	}
	
	public String getNome() {
		return super.getNome();
	}

}
