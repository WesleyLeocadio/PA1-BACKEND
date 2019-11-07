package com.pa1.backend.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.tools.rmi.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Usuario;
import com.pa1.backend.dto.NewUsuarioDTO;
import com.pa1.backend.dto.UsuarioDTO;
import com.pa1.backend.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResouce {

	@Autowired
	private UsuarioService service;

	@ApiOperation("Buscar Usuário pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(
			@ApiParam("Id do Usuário")
			@PathVariable Usuario id) {
		Usuario obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	public Usuario findByIdTeste(Usuario id) {
		Usuario obj = service.buscar(id);
		return obj;
	}

	@ApiOperation("Listar todos os Usuários")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list= service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	@ApiOperation("Listar todos os usuários com perfis = FUNCIONARIO")
	@RequestMapping(path = {"/funcionarios"},method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAllFuncionarios() {
		List<Usuario> list= service.findAllFuncionarios();
		return ResponseEntity.ok().body(list);

	}

	@ApiOperation("Listar todos os usuários com perfis = USUARIO")
	@RequestMapping(path = {"/usuarios"},method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>>findAllUsuarios () {
		List<Usuario> list= service.findAllUsuario();
		return ResponseEntity.ok().body(list);

	}

	@ApiOperation("Cadastrar Usuário")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(
			@ApiParam("Objeto de Usuário")
			@Valid
			@RequestBody NewUsuarioDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation("Atualizar Usuário")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(
			@ApiParam("Objeto de Usuário")
			@Valid
			@RequestBody UsuarioDTO objDto,
			@ApiParam("Id do Usuário")
			@PathVariable Integer id) {
		Usuario obj = service.fromDTO(objDto);
		obj.setIdUsuario(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Deletar Usuário")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(
			@ApiParam("Id do Usuário")
			@PathVariable Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
