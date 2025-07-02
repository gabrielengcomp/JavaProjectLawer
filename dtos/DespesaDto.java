package dtos;

public class DespesaDto {

	private String data;
	private String descricao;
	private String valor;

	public DespesaDto() {
	}

	public DespesaDto(String data, String descricao, String valor) {
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
