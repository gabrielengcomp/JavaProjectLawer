package domain;

import java.io.Serializable;

import exceptions.TribunalException;

public class Tribunal implements Serializable {
	
	private static final long serialVersionUID = -7969009380079714977L;
	
	private final String sigla;
	private String nome;
	private String secao;

	public Tribunal(String sigla, String nome, String secao) throws TribunalException {
		this.sigla = sigla;
		this.setNome(nome);
		this.setSecao(secao);
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws TribunalException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new TribunalException("Nome é obrigatória.");
		}
		this.nome = nome;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) throws TribunalException {

		if (secao == null || secao.trim().isEmpty()) {
			throw new TribunalException("Seção é obrigatória.");
		}
		this.secao = secao;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");

		sb.append("\n\nTribunal\nSigla: " + getSigla() + "\nDescricao: " + getNome() + "\nSeção: " + getSecao());

		sb.append("\n\n");
		return sb.toString();
	}

}
