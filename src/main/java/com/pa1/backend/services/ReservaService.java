package com.pa1.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.repositories.EspacoRepository;
import com.pa1.backend.repositories.ReservaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ReservaService {
	@Autowired //vai ser instanciado autmatico
	private ReservaRepository repo;

	//Listar Reservas de uma data especifica
	public List<Reserva> findByDate(Date d){
		return repo.findByDate(d);
	}

	//Listar todas as reservas
	public List<Reserva> findAll() {
		return repo.findAll();
	}

	//Listar reservas de um Espaco especifico
	public List<Reserva> findByEspaco(Espaco e){
		return repo.findByEspaco(e);
	}

	//Buscar reserva pelo id
	public Reserva buscar(Integer id) {
		Reserva obj = repo.findOne(id);
		return obj;
	}

	public Reserva insert(Reserva obj) {
		obj.setIdReserva(null);
		return repo.save(obj);
	}
	
	public Reserva fromDTO(ReservaDTO objDto) {
		Reserva r1 = new Reserva(null,objDto.getDataReservaInicio(), objDto.getDataReservaFim(),objDto.getHorarios(),
				objDto.getResponsavel(),objDto.getEspaco());
		return r1;
		
	}

}
