package domain;

import java.io.Serializable;
import java.util.Date;

import exceptions.AudienciaException;

public class Audiencia implements Serializable {

	private static final long serialVersionUID = -319067331499951407L;

	private final Date data;
	private final String recomendacao;
	private final Advogado advogado;

	public Audiencia(Date data, String recomendacao, Advogado advogado) throws AudienciaException {
		if (data == null || recomendacao == null || advogado == null) {
			throw new AudienciaException("Cadastro de audiencia inadequado");
		}
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
