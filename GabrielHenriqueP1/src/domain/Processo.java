package domain;

import java.util.ArrayList;
import java.util.Date;

import enumarations.EFaseProcesso;
import exceptions.DespesaException;

public class Processo {
	private final long numero;
	private final Date dataAbertura;
	private EFaseProcesso fase;
	private Tribunal tribunal;
	private Pessoa cliente;
	private Pessoa parteContraria;
	
	private ArrayList<Audiencia> audiencias = new ArrayList<>();
	private ArrayList<Despesa> custas = new ArrayList<>();	
	
	public Processo(long numero) {
		this.numero = numero;
		this.dataAbertura = new Date();
		fase = EFaseProcesso.INICIAL;
	}
	public EFaseProcesso getFase() {
		return fase;
	}
	public void setFase(EFaseProcesso fase) {
		this.fase = fase;
	}
	public long getNumero() {
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
	public Pessoa getCliente() {
		return cliente;
	}
	
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public Pessoa getParteContraria() {
		return parteContraria;
	}
	public void setParteContraria(Pessoa parteContraria) {
		this.parteContraria = parteContraria;
	}
	
	public void addAudiencia(Date data, String recomendacao, Advogado advogado) {
		Audiencia audiencia = new Audiencia(data, recomendacao, advogado);
		audiencias.add(audiencia);
	}
	public void addDespesa(Date data, String descricao, double valor) throws DespesaException{
		Despesa despesa = new Despesa(data, descricao, valor);
		custas.add(despesa);
	}
	
	public String getAudiencias(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Audiencia audiencia : audiencias) {
			sb.append("Data Audiencia: " + audiencia.getData() + "\nAdvogado: " + audiencia.getNomeAdvogado() + "\nRecomendacao: " + audiencia.getRecomendacao());
			sb.append("\n\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public String getCustas(){
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		
		sb.append("IdProcesso: " + this.getNumero() + "  Fase do Processo: " + this.getFase());
		sb.append("\n\nCliente: " + cliente.getNome() + "\nParte Contraria: " + parteContraria.getNome());
		sb.append("\n\nTribunal\nSigla: " + tribunal.getSigla() + "\nDescricao: " + tribunal.getDescricao() + "\nSeção: " + tribunal.getSecao());
		
		sb.append("\n\n");		
		return sb.toString();
	}
}
