package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import contracts.IPessoa;
import domain.Advogado;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import dtos.AdvogadoDto;
import dtos.PessoaFisicaDto;
import dtos.PessoaJuridicaDto;
import exceptions.AdvogadoException;
import exceptions.CnpjException;
import exceptions.CpfException;
import exceptions.EmailException;
import exceptions.PessoaException;
import shared.Cnpj;
import shared.Cpf;
import shared.Email;

public class PessoaController implements Serializable {

	private static final long serialVersionUID = -1219816479916038521L;

	private Map<String, IPessoa> pessoas;
	private Map<String, PessoaFisica> pessoasFisicas;
	private Map<String, PessoaJuridica> pessoasJuridicas;
	private Map<String, Advogado> advogados;

	protected PessoaController() {
		pessoas = new TreeMap<>();
		pessoasFisicas = new TreeMap<>();
		pessoasJuridicas = new TreeMap<>();
		advogados = new TreeMap<>();
	}

	public void createPessoaFisica(PessoaFisicaDto dto) throws PessoaException, EmailException, CpfException {

		if (pessoasFisicas.get(dto.getCpf()) != null) {
			throw new PessoaException("Já existe pessoa fisica cadastrada para o cpf: " + dto.getCpf());
		}

		Cpf cpf = new Cpf(dto.getCpf());
		Email email = new Email(dto.getEmail());

		PessoaFisica pf = new PessoaFisica(dto.getNome(), email, dto.getTelefone(), cpf);

		pessoas.put(pf.getCadastroRF(), pf);
		pessoasFisicas.put(pf.getCadastroRF(), pf);

		MainController.save();
	}

	public void createPessoaJuridica(PessoaJuridicaDto dto)
			throws PessoaException, EmailException, CnpjException, CpfException {

		if (pessoasJuridicas.get(dto.getCnpj()) != null) {
			throw new PessoaException("Já existe pessoa cadastrada para o cnpj: " + dto.getCnpj());
		}

		Cnpj cnpj = new Cnpj(dto.getCnpj());
		Email email = new Email(dto.getEmail());

		if (pessoasFisicas.get(dto.getCpfPreposto()) == null) {
			throw new PessoaException(
					"Preposto: Não existe pessoa fisica cadastrada para o cpf: " + dto.getCpfPreposto());
		}

		PessoaFisica preposto = pessoasFisicas.get(dto.getCpfPreposto());

		PessoaJuridica pj = new PessoaJuridica(dto.getNome(), email, dto.getTelefone(), cnpj, preposto);

		pessoas.put(pj.getCadastroRF(), pj);
		pessoasJuridicas.put(pj.getCadastroRF(), pj);

		MainController.save();
	}

	public void createAdvogado(AdvogadoDto dto) throws PessoaException, CpfException, AdvogadoException {
		if (pessoasFisicas.get(dto.getCpf()) == null) {
			throw new PessoaException("Não existe pessoa fisica cadastrada para o cpf: " + dto.getCpf());
		}

		PessoaFisica pf = pessoasFisicas.get(dto.getCpf());

		Advogado adv = new Advogado(pf, dto.getRegistro());

		advogados.put(dto.getRegistro(), adv);

		MainController.save();
	}

	public void updatePessoaFisica(PessoaFisicaDto dto) throws PessoaException, EmailException {

		PessoaFisica pf = pessoasFisicas.get(dto.getCpf());

		if (pf == null)
			throw new PessoaException("Não tem pessoa cadastrada para o CPF: " + dto.getCpf());

		Email email = new Email(dto.getEmail());

		pf.setNome(dto.getNome());
		pf.setEmail(email);
		pf.setTelefone(dto.getTelefone());

		MainController.save();
	}

	public void updatePessoaJuridica(PessoaJuridicaDto dto) throws PessoaException, EmailException, CpfException {

		PessoaJuridica pj = pessoasJuridicas.get(dto.getCnpj());

		if (pj == null)
			throw new PessoaException("Não tem pessoa cadastrada para o CNPJ: " + dto.getCnpj());

		Email email = new Email(dto.getEmail());

		if (pessoasFisicas.get(dto.getCpfPreposto()) == null) {
			throw new PessoaException(
					"Preposto: Não existe pessoa fisica cadastrada para o cpf: " + dto.getCpfPreposto());
		}

		PessoaFisica preposto = pessoasFisicas.get(dto.getCpfPreposto());

		pj.setNome(dto.getNome());
		pj.setEmail(email);
		pj.setTelefone(dto.getTelefone());
		pj.setPreposto(preposto);

		MainController.save();
	}

	public IPessoa getPessoa(String cadastro) {
		IPessoa pessoa = pessoas.get(cadastro);
		return pessoa;
	}

	public PessoaFisicaDto getPessoaFisica(String cpf) throws PessoaException {

		PessoaFisica pf = pessoasFisicas.get(cpf);

		if (pf == null)
			throw new PessoaException("Não tem pessoa cadastrada para o cpf: " + cpf);

		PessoaFisicaDto pessoaFisicaDto = new PessoaFisicaDto(pf.getNome(), pf.getEmail(), pf.getCadastroRF(),
				pf.getTelefone());

		return pessoaFisicaDto;
	}

	public PessoaJuridicaDto getPessoaJuridica(String cnpj) throws PessoaException {

		PessoaJuridica pj = pessoasJuridicas.get(cnpj);

		if (pj == null)
			throw new PessoaException("Não tem pessoa cadastrada para o cnpj: " + cnpj);

		PessoaJuridicaDto pessoaJuridicaDto = new PessoaJuridicaDto(pj.getNome(), pj.getEmail(), pj.getCadastroRF(),
				pj.getTelefone(), pj.getPreposto().getCadastroRF());

		return pessoaJuridicaDto;
	}

	public AdvogadoDto getAdvogadoDto(String registro) throws AdvogadoException {

		Advogado adv = advogados.get(registro);

		if (adv == null)
			throw new AdvogadoException("Não tem pessoa cadastrada para o registro: " + registro);

		PessoaFisica pf = pessoasFisicas.get(adv.getCadastroRF());

		AdvogadoDto advogadoDto = new AdvogadoDto(adv.getRegistro(), adv.getCadastroRF(), pf.getNome(), pf.getEmail(),
				pf.getTelefone());

		return advogadoDto;
	}
	
	public Advogado getAdvogado(String registro) throws AdvogadoException {
		Advogado adv = advogados.get(registro);
		
		if (adv == null)
			throw new AdvogadoException("Não tem pessoa cadastrada para o registro: " + registro);

		return adv;
	}
	
	public void removePessoaFisica(String cpf) {
		pessoasFisicas.remove(cpf);
		pessoas.remove(cpf);
	}
	
	public void removePessoaJuridica(String cnpj) {
		pessoasJuridicas.remove(cnpj);
		pessoas.remove(cnpj);
	}

	public void removeAdvogado(String registro) {
		advogados.remove(registro);
	}
	
	public List<PessoaFisicaDto> getPessoasFisicas() {

		List<PessoaFisicaDto> lista = new ArrayList<>();

		for (PessoaFisica pf : pessoasFisicas.values()) {
			PessoaFisicaDto dto = new PessoaFisicaDto(pf.getNome(), pf.getEmail(), pf.getCadastroRF(),
					pf.getTelefone());
			lista.add(dto);
		}

		return lista;
	}

	public List<PessoaJuridicaDto> getPessoasJuridicas() {

		List<PessoaJuridicaDto> lista = new ArrayList<>();

		for (PessoaJuridica pj : pessoasJuridicas.values()) {
			PessoaJuridicaDto dto = new PessoaJuridicaDto(pj.getNome(), pj.getEmail(), pj.getTelefone(),
					pj.getCadastroRF(), pj.getPreposto().getCadastroRF());
			lista.add(dto);
		}

		return lista;
	}

	public List<AdvogadoDto> getAdvogados() {
		List<AdvogadoDto> lista = new ArrayList<>();
		for (Advogado adv : advogados.values()) {
			PessoaFisica pf = pessoasFisicas.get(adv.getCadastroRF());
			if (pf != null) {
				AdvogadoDto dto = new AdvogadoDto(adv.getRegistro(), adv.getCadastroRF(), pf.getNome(), pf.getEmail(),
						pf.getTelefone());
				lista.add(dto);
			}
		}
		return lista;
	}
}
