package com.pa1.backend.resources;

import java.net.URI;
import java.util.List;

import com.pa1.backend.dto.EspacoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.services.EspacoService;

@RestController
@RequestMapping(value = "/espacos")
public class EspacoResouce {

	@Autowired
	private EspacoService service;

	@ApiOperation("Buscar Espaço pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(
			@ApiParam("Id do Espaço")
			@PathVariable Espaco id) {
		Espaco obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	public Espaco findByIdTeste(Espaco id) {
		Espaco obj = service.buscar(id);
		return obj;
	}

	@ApiOperation("Listar todos os Espaços")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findAll() {
		List<Espaco> list= service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Listar todos os Espaços Habilitados")
	@RequestMapping(path = {"/habilitados"}, method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findByHabilitado() {
		List<Espaco> list= service.findByHabilitado();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Listar todos os Espaços Desabilitados")
	@RequestMapping(path = {"/desabilitados"}, method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findByDesabilitado() {
		List<Espaco> list= service.findByDesabilitado();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Listar todos os Espaços que tem Computador")
	@RequestMapping(path = {"/computadores"}, method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findByComputadores() {
		List<Espaco> list= service.findByDesabilitado();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Listar todos os Espaços que nao tem Computador")
	@RequestMapping(path = {"/naocomputadores"}, method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findByNComputadores() {
		List<Espaco> list= service.findByDesabilitado();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Cadastrar um Espaço")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertEspaco(
			@ApiParam("Objeto de Espaço")
			@RequestBody EspacoDTO objDto) {
		Espaco obj = service.fromDTO(objDto);
		List<Espaco> list= service.findByLocalizacao(obj.getLocalizacao());

		for(Espaco espaco: list){
			System.out.println("Pesquisando");
			if(espaco.getNome().equals(obj.getNome())){
				return ResponseEntity.noContent().build();
			}
		}
		obj = service.insert(obj);
		System.out.println("Cadastrou o espaco");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation("Marcar espaço como especial")
	@RequestMapping(path = {"/especial"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> especialEspaco(
			@ApiParam("Id da Reserva")
			@RequestParam Espaco id
	){
		Espaco obj=service.buscar(id);
		obj.setEspecial(true);
	    service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Desabilitar espaço")
	@RequestMapping(path = {"/desabilitar"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> desabilitarEspaco(
			@ApiParam("Id da Reserva")
			@RequestParam Espaco id
	){
		Espaco obj=service.buscar(id);
		obj.setDesabilitado(true);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Habilitar espaço")
	@RequestMapping(path = {"/habilitar"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> habilitarEspaco(
			@ApiParam("Id da Reserva")
			@RequestParam Espaco id
	){
		Espaco obj=service.buscar(id);
		obj.setDesabilitado(false);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
