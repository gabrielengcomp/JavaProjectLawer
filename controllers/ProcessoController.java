package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Processo;
import dtos.AudienciaDto;
import dtos.DespesaDto;
import dtos.ProcessoDto;
import exceptions.AdvogadoException;
import exceptions.AudienciaException;
import exceptions.DespesaException;
import exceptions.PessoaException;
import exceptions.ProcessoException;
import exceptions.TribunalException;

public class ProcessoController implements Serializable {

	private static final long serialVersionUID = 4054614483258651130L;

	private Map<String, Processo> processos;
	private final TribunalController tribunalController;
	private final PessoaController pessoaController;

	protected ProcessoController(TribunalController tribunalController, PessoaController pessoaController) {
        this.tribunalController = tribunalController;
        this.pessoaController = pessoaController;
        this.processos = new TreeMap<>();
	}

	public void createProcesso(ProcessoDto dto) throws ProcessoException, TribunalException, PessoaException {
		
		if (processos.get(dto.getNumero()) != null)
			throw new ProcessoException("Já existe processo cadastrado com o número: " + dto.getNumero());

		Processo proc = new Processo(dto.getNumero(), dto.getDataAbertura(),
				tribunalController.getTribunal(dto.getTribunal()), pessoaController.getPessoa(dto.getCliente()),
				pessoaController.getPessoa(dto.getParteContraria()));
		
		proc.setFase(dto.getFase());

		processos.put(dto.getNumero(), proc);

		MainController.save();
	}

	public void updateProcesso(ProcessoDto dto) throws ProcessoException, TribunalException {
		Processo proc = processos.get(dto.getNumero());
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + dto.getNumero());

		proc.setTribunal(tribunalController.getTribunal(dto.getTribunal()));
		proc.setFase(dto.getFase());
		proc.setCliente(pessoaController.getPessoa(dto.getCliente()));
		proc.setParteContraria(pessoaController.getPessoa(dto.getParteContraria()));

		MainController.save();
	}

	public void addDespesa(DespesaDto dto) throws ProcessoException, DespesaException {
		Processo proc = processos.get(dto.getNumProcesso());
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + dto.getNumProcesso());

		proc.addDespesa(dto.getData(), dto.getDescricao(), dto.getValor());

		MainController.save();
	}

	public void addAudiencia(AudienciaDto dto) throws ProcessoException, AudienciaException, AdvogadoException {
		Processo proc = processos.get(dto.getNumProcesso());
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + dto.getNumProcesso());

		proc.addAudiencia(dto.getData(), dto.getRecomendacao(), pessoaController.getAdvogado(dto.getRegistro()));

		MainController.save();
	}

	public double totalCustas(String numero) throws ProcessoException {
		Processo proc = processos.get(numero);
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);

		return proc.getTotalCustas();
	}

	public String listarCustas(String numero) throws ProcessoException {
		Processo proc = processos.get(numero);
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);

		
		return proc.getCustas();
	}

	public String listarAudiencias(String numero) throws ProcessoException {
		Processo proc = processos.get(numero);
		if (proc == null)
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);

		return proc.getAudiencias();
	}

	public void removeProcesso(String numero) {
		processos.remove(numero);
	}

	public ProcessoDto getProcesso(String numero) throws ProcessoException {

		Processo proc = processos.get(numero);

		if (proc == null)
			throw new ProcessoException("Não tem processo cadastrado com o numero: " + numero);

		ProcessoDto dto = new ProcessoDto(proc.getNumero(), proc.getDataAbertura(), proc.getFase(),
				proc.getTribunal().getSigla(), proc.getCliente().getCadastroRF(),
				proc.getParteContraria().getCadastroRF());

		return dto;
	}
	
	public List<ProcessoDto> getProcessosRF(String cadastroRF) throws ProcessoException {
		List<ProcessoDto> lista = new ArrayList<>();
		for (Processo proc : processos.values()) {
			if (proc.getCliente().getCadastroRF().equals(cadastroRF)) {
				ProcessoDto dto = new ProcessoDto(proc.getNumero(), proc.getDataAbertura(), proc.getFase(),
						proc.getTribunal().getSigla(), proc.getCliente().getCadastroRF(),
						proc.getParteContraria().getCadastroRF());
				lista.add(dto);
			}
		}
		return lista;
	}

	public List<ProcessoDto> getProcessos() {
		List<ProcessoDto> lista = new ArrayList<>();
		for (Processo proc : processos.values()) {
			ProcessoDto dto = new ProcessoDto(proc.getNumero(), proc.getDataAbertura(), proc.getFase(),
					proc.getTribunal().getSigla(), proc.getCliente().getCadastroRF(),
					proc.getParteContraria().getCadastroRF());
			lista.add(dto);
		}
		return lista;
	}

}
