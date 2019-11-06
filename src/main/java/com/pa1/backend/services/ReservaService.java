package com.pa1.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.repositories.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repo;

	public Reserva fromDTO(ReservaDTO objDto) {
		Reserva r1 = new Reserva(null,
				objDto.getDataReservaInicio(),
				objDto.getDataReservaFim(),
				objDto.getHorarios(),
				objDto.getEspaco(),
				objDto.getUsuario(),
				objDto.getAprovada(),
				objDto.getCancelada()
		);
		return r1;
	}

	public Reserva insert(Reserva obj) {
		obj.setIdReserva(null);
		return repo.save(obj);
	}

	public Reserva update(Reserva obj){
		return  repo.save(obj);
	}

	public List<Reserva> findByDate(Date d){
		return repo.findByDate(d);
	}

	public List<Reserva> findAll() {
		return repo.findAll();
	}

	public List<Reserva> findByEspaco(Espaco e){
		return repo.findByEspaco(e);
	}

	public Reserva buscar(Integer id) {
		Reserva obj = repo.findOne(id);
		return obj;
	}

	public List<Reserva> findByAprovadas(){
		return repo.findByAprovadas();
	}

	public List<Reserva> findByPendentes(){
		return repo.findByPendentes();
	}

	public List<Reserva> findByReserva(Integer idEspaco, Date d){
		return repo.findByReserva(idEspaco,d);
	}

}
