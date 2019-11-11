package com.pa1.backend.services;

import com.pa1.backend.dto.EspacoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pa1.backend.domain.Espaco;
import com.pa1.backend.repositories.EspacoRepository;
import java.util.List;

@Service
public class EspacoService {

	@Autowired
	private EspacoRepository repo;

	public Espaco fromDTO(EspacoDTO objDto) {
		Espaco e = new Espaco(null,
				objDto.getNome(),
				objDto.getDescricao(),
				objDto.getLocalizacao(),
				objDto.getResponsavel(),
				objDto.isEspecial(),
				objDto.getJustificativa(),
				objDto.isDesabilitado(),
				objDto.isComputadores(),
				objDto.getQtdPessoas(),
				objDto.getRamal()
		);
		return e;
	}

	public Espaco insert(Espaco obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Espaco update(Espaco obj) {
		return repo.save(obj);
	}

	public Espaco buscar(Espaco e) {
		Espaco obj = repo.findOne(e.getId());
		return obj;
	}

	public List<Espaco> findAll() {
		return repo.findAll();
	}

	public List<Espaco> findByHabilitado() {
		return repo.findByHabilitado();
	}

	public List<Espaco> findByDesabilitado() {
		return repo.findByDesabilitado();
	}

	public List<Espaco> findByLocalizacao(String local){
		return repo.findByLocalizacao(local);
	}

	public List<Espaco> findByComputadores() {
		return repo.findByComputadores();
	}

	public List<Espaco> findByNComputadores() {
		return repo.findByNComputadores();
	}

}
