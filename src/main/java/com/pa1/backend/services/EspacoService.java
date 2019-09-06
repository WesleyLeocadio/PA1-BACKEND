package com.pa1.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.repositories.EspacoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EspacoService {
	@Autowired //vai ser instanciado autmatico
	private EspacoRepository repo;
	 //uma operacao q buscar um  espaco por codigo

	public Espaco buscar(Integer id) {
		Espaco obj = repo.findOne(id);
		return obj;
}
}
