package domain;

import java.util.Date;

public class Audiencia {
	private final Date data;
	private final String recomendacao;
	private final Advogado advogado;

	public Audiencia(Date data, String recomendacao, Advogado advogado) {
		this.data = data;
		this.recomendacao = recomendacao;
		this.advogado = advogado;
	}

	public Date getData() {
		return data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public String getNomeAdvogado() {
		return advogado.getNome();
	}
}
