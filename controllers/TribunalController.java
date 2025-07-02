package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Tribunal;
import dtos.TribunalDto;
import exceptions.TribunalException;

public class TribunalController implements Serializable {

	private static final long serialVersionUID = -4125026648925415158L;
	
	private Map<String, Tribunal> tribunais;

	protected TribunalController() {
		tribunais = new TreeMap<>();
	}

	public void createTribunal(TribunalDto tribunalDto) throws TribunalException {

		if (tribunais.get(tribunalDto.getSigla()) != null) {
			throw new TribunalException("Já existe tribunal cadastrado para a sigla: " + tribunalDto.getSigla());
		}

		Tribunal t = new Tribunal(tribunalDto.getSigla(), tribunalDto.getNome(), tribunalDto.getSecao());

		tribunais.put(t.getSigla(), t);
		
		MainController.save();
	}

	public void updateTribunal(TribunalDto tribunalDto) throws TribunalException {

		Tribunal tribunal = tribunais.get(tribunalDto.getSigla());

		if (tribunal == null)
			throw new TribunalException("Não tem tribunal cadastrado para a sigla: " + tribunalDto.getSigla());

		tribunal.setNome(tribunalDto.getNome());
		tribunal.setSecao(tribunalDto.getSecao());
		
		MainController.save();
	}

	public TribunalDto getTribunal(String sigla) throws TribunalException {

		Tribunal tribunal = tribunais.get(sigla);

		if (tribunal == null)
			throw new TribunalException("Não tem tribunal cadastrado para a sigla: " + sigla);

		TribunalDto tribunalDto = new TribunalDto(tribunal.getSigla(), tribunal.getNome(), tribunal.getSecao());

		return tribunalDto;
	}

	public List<TribunalDto> getTribunais() {

		List<TribunalDto> lista = new ArrayList<>();

		TribunalDto tribunalDto;

		for (Tribunal t : tribunais.values()) {
			tribunalDto = new TribunalDto(t.getSigla(), t.getNome(), t.getSecao());
			lista.add(tribunalDto);
		}

		return lista;
	}

}