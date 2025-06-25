package controllers;

import java.util.Map;
import java.util.TreeMap;

import domain.Tribunal;
import dtos.TribunalDto;
import exceptions.TribunalException;

public class TribunalController {
	private Map<String, Tribunal> tribunais;

	public TribunalController() {
		tribunais = new TreeMap<String, Tribunal>();
	}

	public void addTribunal(TribunalDto tribunalDto) throws TribunalException {
		Tribunal t = new Tribunal(tribunalDto.getSigla(), tribunalDto.getNome(), tribunalDto.getSecao());
		tribunais.put(t.getSigla(), t);
	}
	
	public String getTribunal(String sigla) throws TribunalException{
		Tribunal t = tribunais.get(sigla);
		if(t==null) {
			
		}
		return t.toString();
	}
}
