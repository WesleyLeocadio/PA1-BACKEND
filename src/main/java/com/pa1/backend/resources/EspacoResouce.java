package com.pa1.backend.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.pa1.backend.dto.EspacoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.services.EspacoService;

//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/espacos") // vai responder por este endPoint
public class EspacoResouce {

	@Autowired
	private EspacoService service;

	//Listar espaco por id
	// pra ser uma função REST é preciso associar com algum dos verbos do http
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // como tô obtendo dados uso o metodo get
	public ResponseEntity<?> find(@PathVariable Integer id) {// ResponseEntity<?> tipo especial do spring que ja
																// encapsula varias informações de uma respota http para
																// um servico rest
		Espaco obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	//Listar todos os espaços
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Espaco>> findAll() {
		List<Espaco> list= service.findAll();
		return ResponseEntity.ok().body(list);

	}

	//Cadastrar Espaco
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody EspacoDTO objDto) {
		Espaco obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspaco()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//Marcar espaco como especial
	/*
	#TAVA DANDO ERRO 500 -> Internal Server Error
	@RequestMapping(path = {"/id"}, method = RequestMethod.PUT)
	public ResponseEntity<?> updateEspaco(@RequestBody EspacoDTO objDto ,@PathVariable Integer id) {

		Espaco obj = service.buscar(id);

		if(obj == null)
			return ResponseEntity.notFound().build();

		service.update(obj,id);

		return ResponseEntity.noContent().build();

	}
	//Fazendo update com POST
	@RequestMapping(path = {"/id"}, method = RequestMethod.POST)
	public ResponseEntity<Void> updateEspaco(@RequestBody EspacoDTO objDto, @PathVariable Integer id) {
		Espaco obj = service.fromDTO(objDto);
		obj.setIdEspaco(id);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspaco()).toUri();
		return ResponseEntity.created(uri).build();
	}
	*/
}
