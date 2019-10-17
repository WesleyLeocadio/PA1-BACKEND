package com.pa1.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pa1.backend.domain.Usuario;
import com.pa1.backend.repositories.UsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {
	@Autowired //vai ser instanciado autmatico
	private UsuarioRepository repo;

	//listar espacos
	public List<Usuario> findAll() {
		return repo.findAll();
	}

	//uma operacao q buscar um  espaco por codigo
	public Usuario buscar(Integer id) {
		Usuario obj = repo.findOne(id);
		return obj;
	}

	

}
