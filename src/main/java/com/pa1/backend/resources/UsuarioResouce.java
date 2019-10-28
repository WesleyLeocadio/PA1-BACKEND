package com.pa1.backend.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.tools.rmi.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.domain.Usuario;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.dto.UsuarioDTO;
import com.pa1.backend.services.ReservaService;
import com.pa1.backend.services.UsuarioService;

//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResouce {

	@Autowired
	private UsuarioService service;

	@ApiOperation("Buscar usuario pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(
			@ApiParam("Id do objeto cadastrado do usuario")
			@PathVariable Integer id) {
		Usuario obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation("Listar todos os usuarios")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list= service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	@ApiOperation("Listar todos os funcionarios")
	@RequestMapping(path = {"/funcionarios"},method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAllFuncionarios() {
		List<Usuario> list= service.findAllFuncionarios();
		return ResponseEntity.ok().body(list);

	}
	@ApiOperation("Listar todos os usuarios")
	@RequestMapping(path = {"/usuarios"},method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>>findAllUsuarios () {
		List<Usuario> list= service.findAllUsuario();
		return ResponseEntity.ok().body(list);

	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
		System.out.print("chegoooooooooooooooooooooooouuuuu "+service.fromDTO(objDto).toString());
		Usuario obj = service.fromDTO(objDto);
		obj.setIdUsuario(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
