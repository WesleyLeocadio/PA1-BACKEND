package com.pa1.backend.services;

import com.pa1.backend.dto.EspacoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.repositories.EspacoRepository;

import java.util.List;

@Service
public class EspacoService {
	@Autowired //vai ser instanciado autmatico
	private EspacoRepository repo;

	//listar espacos
	public List<Espaco> findAll() {
		return repo.findAll();
	}

	public List<Espaco> findByHabilitado() {
		return repo.findByHabilitado();
	}

	public List<Espaco> findByDesabilitado() {
		return repo.findByDesabilitado();
	}

	//uma operacao q buscar um  espaco por codigo
	public Espaco buscar(Integer id) {
		Espaco obj = repo.findOne(id);
		return obj;
	}

	//mudar especial de espaco
	public Espaco update(Espaco obj) {
		return repo.save(obj);
	}

	//cadastrar espaco
	public Espaco insert(Espaco obj) {
		obj.setIdEspaco(null);
		return repo.save(obj);
	}

	//Obj DTO de Espaco
	public Espaco fromDTO(EspacoDTO objDto) {
		Espaco e = new Espaco(null,objDto.getEspacoNome(),objDto.getEspacoDescricao(),
				objDto.getEspacoLocalizacao(),objDto.isEspacoEspecial(),objDto.getEspacoResponsavel(),objDto.isEspacoDesabilitado());
		return e;
	}

	//busca localizacao
	public List<Espaco> findByLocalizacao(String local){
		return repo.findByLocalizacao(local);
	}

}
