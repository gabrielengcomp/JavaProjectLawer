package dtos;

public class AudienciaDto {

	private String data;
	private String recomendacao;
	private String registro;

	public AudienciaDto() {
	}

	public AudienciaDto(String data, String recomendacao, String registro) {
		this.data = data;
		this.recomendacao = recomendacao;
		this.registro = registro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

}
