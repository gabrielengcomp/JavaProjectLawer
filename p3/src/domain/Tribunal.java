package domain;

import exceptions.TribunalException;

public class Tribunal {
	private final String sigla;
	private final String nome;
	private final String secao;
	
	public Tribunal(String sigla, String nome, String secao) throws TribunalException {
		if (sigla == null || nome == null || secao == null) {
			throw new TribunalException("Cadastro de tribunal inadequado");
		}
		this.sigla = sigla;
		this.nome = nome;
		this.secao = secao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getSecao() {
		return secao;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");

		sb.append("\n\nTribunal\nSigla: " + getSigla() + "\nDescricao: " + getNome()
				+ "\nSeção: " + getSecao());

		sb.append("\n\n");
		return sb.toString();
	}
	
}
