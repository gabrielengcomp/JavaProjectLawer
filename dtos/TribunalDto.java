package dtos;

public class TribunalDto {
	private String sigla;
	private String nome;
	private String secao;

	public TribunalDto() {
	}

	public TribunalDto(String sigla, String nome, String secao) {
		this.sigla = sigla;
		this.nome = nome;
		this.secao = secao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setDescricao(String nome) {
		this.nome = nome;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

}
