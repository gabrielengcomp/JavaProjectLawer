package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import contracts.IPessoa;
import enumarations.EFaseProcesso;
import exceptions.AudienciaException;
import exceptions.DespesaException;
import exceptions.PessoaException;
import exceptions.ProcessoException;
import exceptions.TribunalException;

public class Processo implements Serializable {

	private static final long serialVersionUID = 7054886663918955262L;

	private final String numero;
	private final Date dataAbertura;
	private EFaseProcesso fase;
	private Tribunal tribunal;
	private IPessoa cliente;
	private IPessoa parteContraria;

	private ArrayList<Audiencia> audiencias = new ArrayList<>();
	private ArrayList<Despesa> custas = new ArrayList<>();

	public Processo(String numero, Date dataAbertura, Tribunal tribunal, IPessoa cliente, IPessoa parteContraria)
			throws ProcessoException, TribunalException, PessoaException {
		if (numero == null)
			throw new ProcessoException("Numero do processo deve existir");
		if (tribunal == null)
			throw new TribunalException("Processo deve ser criado com Tribunal");
		if (cliente == null || parteContraria == null)
			throw new PessoaException("Processo deve ser criado declarando partes envolvidas");

		this.tribunal = tribunal;
		this.cliente = cliente;
		this.parteContraria = parteContraria;
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		fase = EFaseProcesso.INICIAL;
	}

	public EFaseProcesso getFase() {
		return fase;
	}

	public void setFase(EFaseProcesso fase) {
		this.fase = fase;
	}

	public String getNumero() {
		return numero;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

	public IPessoa getCliente() {
		return cliente;
	}

	public void setCliente(IPessoa cliente) {
		this.cliente = cliente;
	}

	public IPessoa getParteContraria() {
		return parteContraria;
	}

	public void setParteContraria(IPessoa parteContraria) {
		this.parteContraria = parteContraria;
	}

	public void addAudiencia(Date data, String recomendacao, Advogado advogado) throws AudienciaException {
		Audiencia audiencia = new Audiencia(data, recomendacao, advogado);
		audiencias.add(audiencia);
	}

	public void addDespesa(Date data, String descricao, double valor) throws DespesaException {
		Despesa despesa = new Despesa(data, descricao, valor);
		custas.add(despesa);
	}

	public String getAudiencias() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Audiencia audiencia : audiencias) {
			sb.append("Data Audiencia: " + audiencia.getData() + "\nAdvogado: " + audiencia.getNomeAdvogado()
					+ "\nRecomendacao: " + audiencia.getRecomendacao());
			sb.append("\n\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public String getCustas() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Despesa despesa : custas) {
			sb.append("\nData: " + despesa.getData() + "\nValor: " + despesa.getValor());
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public double getTotalCustas() {
		double total = 0.0;

		for (Despesa despesa : custas) {
			total += despesa.getValor();
		}
		return total;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");

		sb.append("IdProcesso: " + this.getNumero() + "  Fase do Processo: " + this.getFase());
		sb.append("\n\nCliente: " + cliente.getNome() + "\nParte Contraria: " + parteContraria.getNome());
		sb.append("\n\nTribunal\nSigla: " + tribunal.getSigla() + "\nDescricao: " + tribunal.getNome() + "\nSeção: "
				+ tribunal.getSecao());

		sb.append("\n\n");
		return sb.toString();
	}
}
