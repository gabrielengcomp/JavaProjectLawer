package controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Processo;
import domain.Tribunal;
import dtos.PessoaFisicaDto;
import dtos.PessoaJuridicaDto;
import dtos.ProcessoDto;
import dtos.TribunalDto;
import enumarations.EFaseProcesso;
import exceptions.CnpjException;
import exceptions.CpfException;
import exceptions.EmailException;
import exceptions.PessoaException;
import exceptions.ProcessoException;
import exceptions.TribunalException;
import shared.Cnpj;
import shared.Cpf;
import shared.Email;

public class ProcessoController implements Serializable {

	private static final long serialVersionUID = 4054614483258651130L;

	private Map<String, Processo> processos;
	private TribunalController tribunalController = new TribunalController();
	private PessoaController pessoaController = new PessoaController();

	protected ProcessoController() {
		processos = new TreeMap<>();
	}

	public void createProcesso(ProcessoDto dto) throws ProcessoException, ParseException, TribunalException {

		long numero = Long.parseLong(dto.getNumero());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAbertura = sdf.parse(dto.getDataAbertura());

		if (processos.get(dto.getNumero()) != null) {
			throw new ProcessoException("Já existe processo cadastrado com o número: " + dto.getNumero());
		}

		Processo proc = new Processo(numero, dataAbertura);
		processoSetTribunal(dto.getNumero(), dto.getTribunal());
		processoSetFase(dto.getNumero(), dto.getFase());
		// TODO: identificar se cliente e pc são pf ou pj;
		processos.put(dto.getNumero(), proc);
	}

	public void processoSetTribunal(String numero, String sigla) throws TribunalException, ProcessoException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}

		TribunalDto dto = tribunalController.getTribunal(sigla);
		Tribunal t = new Tribunal(dto.getSigla(), dto.getNome(), dto.getSecao());

		processo.setTribunal(t);
	}

	public void processoSetFase(String numero, String fase) throws ProcessoException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}
		EFaseProcesso f = EFaseProcesso.valueOf(fase.toUpperCase());
		processo.setFase(f);
	}

	public void processoSetClientePF(String numero, String cpfDto)
			throws ProcessoException, PessoaException, CpfException, EmailException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}

		PessoaFisicaDto dto = pessoaController.getPessoaFisica(cpfDto);
		Cpf cpf = new Cpf(dto.getCpf());
		Email email = new Email(dto.getEmail());
		PessoaFisica pf = new PessoaFisica(dto.getNome(), email, dto.getTelefone(), cpf);

		processo.setCliente(pf);
	}

	public void processoSetClientePJ(String numero, String cnpjDto)
			throws ProcessoException, PessoaException, CpfException, EmailException, CnpjException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}

		PessoaJuridicaDto dto = pessoaController.getPessoaJuridica(cnpjDto);
		Cnpj cnpj = new Cnpj(dto.getCnpj());
		Email email = new Email(dto.getEmail());

		PessoaFisicaDto prepostoDto = pessoaController.getPessoaFisica(dto.getCpfPreposto());

		Cpf cpfPreposto = new Cpf(prepostoDto.getCpf());
		Email emailPreposto = new Email(prepostoDto.getEmail());

		PessoaFisica preposto = new PessoaFisica(prepostoDto.getNome(), emailPreposto, prepostoDto.getTelefone(),
				cpfPreposto);

		PessoaJuridica pj = new PessoaJuridica(dto.getNome(), email, dto.getTelefone(), cnpj, preposto);

		processo.setCliente(pj);
	}

	public void processoSetPCPF(String numero, String cpfDto)
			throws ProcessoException, PessoaException, CpfException, EmailException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}

		PessoaFisicaDto dto = pessoaController.getPessoaFisica(cpfDto);
		Cpf cpf = new Cpf(dto.getCpf());
		Email email = new Email(dto.getEmail());
		PessoaFisica pf = new PessoaFisica(dto.getNome(), email, dto.getTelefone(), cpf);

		processo.setParteContraria(pf);
	}

	public void processoSetPCPJ(String numero, String cnpjDto)
			throws ProcessoException, PessoaException, CpfException, EmailException, CnpjException {
		Processo processo = processos.get(numero);
		if (processo == null) {
			throw new ProcessoException("Não existe processo cadastrado com o número: " + numero);
		}

		PessoaJuridicaDto dto = pessoaController.getPessoaJuridica(cnpjDto);
		Cnpj cnpj = new Cnpj(dto.getCnpj());
		Email email = new Email(dto.getEmail());

		PessoaFisicaDto prepostoDto = pessoaController.getPessoaFisica(dto.getCpfPreposto());

		Cpf cpfPreposto = new Cpf(prepostoDto.getCpf());
		Email emailPreposto = new Email(prepostoDto.getEmail());

		PessoaFisica preposto = new PessoaFisica(prepostoDto.getNome(), emailPreposto, prepostoDto.getTelefone(),
				cpfPreposto);

		PessoaJuridica pj = new PessoaJuridica(dto.getNome(), email, dto.getTelefone(), cnpj, preposto);

		processo.setParteContraria(pj);
	}

	// TODO: set audiencia, set despesas.

	public ProcessoDto getProcesso(String numero) throws ProcessoException {

		Processo proc = processos.get(numero);

		if (proc == null)
			throw new ProcessoException("Não tem processo cadastrado com o numero: " + numero);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // ou outro formato desejado
		String dataAbertura = sdf.format(proc.getDataAbertura());

		ProcessoDto dto = new ProcessoDto(numero, dataAbertura, proc.getFase().name(), proc.getTribunal().getSigla(),
				proc.getCliente().getCadastroRF(), proc.getParteContraria().getCadastroRF());

		return dto;
	}

}
