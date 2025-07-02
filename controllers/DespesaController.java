package controllers;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import domain.Despesa;
import dtos.DespesaDto;
import dtos.ProcessoDto;
import exceptions.ProcessoException;

public class DespesaController implements Serializable{

	private static final long serialVersionUID = -6485498350202102145L;
	
	private Map<String, Despesa> despesas;

	private ProcessoController processoController = new ProcessoController();
	
	protected DespesaController() {
		despesas = new TreeMap<>();
	}
	
	public void createDespesa(String numero, DespesaDto dto) throws ProcessoException {
		ProcessoDto procDto = processoController.getProcesso(numero);
		
			
		
	}
}
