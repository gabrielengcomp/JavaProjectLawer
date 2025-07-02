package dtos;

public class PessoaJuridicaDto {
	private String nome;
	private String email;
	private String cnpj;
	private String telefone;
	private String cpfPreposto;

	public PessoaJuridicaDto() {
	}

	public PessoaJuridicaDto(String nome, String email, String cnpj, String telefone, String cpfPreposto) {
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.cpfPreposto = cpfPreposto;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpfPreposto() {
		return cpfPreposto;
	}

	public void setCpfPreposto(String preposto) {
		cpfPreposto = preposto;
	}

}
