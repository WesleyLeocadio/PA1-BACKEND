package com.pa1.backend.services;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pa1.backend.domain.Usuario;
import com.pa1.backend.dto.NewUsuarioDTO;
import com.pa1.backend.dto.UsuarioDTO;
import com.pa1.backend.repositories.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired // vai ser instanciado autmatico
	private UsuarioRepository repo;

	// listar espacos
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	// listar espacos
		public List<Usuario> findAllFuncionarios() {
			return repo.findAllTipo(2);
		}

		public List<Usuario> findAllUsuario() {
			return repo.findAllTipo(3);
		}

		public Usuario insert(Usuario obj) {
			obj.setIdUsuario(null);
			obj = repo.save(obj);
			return obj;
		}
		public Usuario fromDTO(NewUsuarioDTO objDto) {
			Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getTelefone(),objDto.getTipoUsuario(),pe.encode(objDto.getSenha()));
			return cli;
		}

	// uma operacao q buscar um espaco por codigo
	public Usuario buscar(Integer id) {
		Usuario obj = repo.findOne(id);
		return obj;
	}
	
	
	
	public Usuario update(Usuario obj) {
		Usuario newObj = repo.findOne(obj.getIdUsuario());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Usuario.class.getName());		}
		return obj;
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há entidades relacionadas");
		}
		
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(null,objDto.getNome(),objDto.getEmail(),objDto.getTelefone(),null,null);
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
