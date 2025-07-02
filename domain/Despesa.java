package domain;

import java.io.Serializable;
import java.util.Date;

import exceptions.DespesaException;
import utils.ValorUtil;

public class Despesa implements Serializable {

	private static final long serialVersionUID = 988416356240661169L;
	
	private final Date data;
	private final String descricao;
	private final double valor;

	public Despesa(Date data, String descricao, double valor) throws DespesaException {
		if (ValorUtil.validaValor(valor)) {
			throw new DespesaException("Valor inserido inválido");
		}
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

}
