package com.pa1.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.repositories.EspacoRepository;
import com.pa1.backend.repositories.ReservaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ReservaService {
	@Autowired //vai ser instanciado autmatico
	private ReservaRepository repo;
	 //uma operacao q buscar um  espaco por codigo

	public List<Reserva> findAll() {
		return repo.findAll();
}
	


}
