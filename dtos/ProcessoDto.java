package dtos;

public class ProcessoDto {

	private String numero;
	private String dataAbertura;
	private String fase;
	private String tribunal;
	private String cliente;
	private String parteContraria;

	public ProcessoDto() {
	}

	public ProcessoDto(String numero, String dataAbertura, String fase, String tribunal, String cliente,
			String parteContraria) {
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		this.fase = fase;
		this.tribunal = tribunal;
		this.cliente = cliente;
		this.parteContraria = parteContraria;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public String getTribunal() {
		return tribunal;
	}

	public void setTribunal(String tribunal) {
		this.tribunal = tribunal;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getParteContraria() {
		return parteContraria;
	}

	public void setParteContraria(String parteContraria) {
		this.parteContraria = parteContraria;
	}

}
