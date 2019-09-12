package com.pa1.backend.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.services.EspacoService;

//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/espacos") // vai responder por este endPoint
public class EspacoResouce {

	@Autowired
	private EspacoService service;

	// pra ser uma função REST é preciso associar com algum dos verbos do http
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // como tô obtendo dados uso o metodo get
	public ResponseEntity<?> find(@PathVariable Integer id) {// ResponseEntity<?> tipo especial do spring que ja
																// encapsula varias informações de uma respota http para
																// um servico rest
		Espaco obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Espaco obj) {

		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspaco())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	// public List<Espaco> listar() {
//		Espaco esp1= new Espaco(1,"lab informatica","sala com computadores","predio de informatica",false,"Laura",false);
//		Espaco esp2= new Espaco(2,"Sala teorica","sala comum","predio de informatica",false,"Taniro",false);
//		List<Espaco> list=new ArrayList<Espaco>();
//		list.add(esp1);
//		list.add(esp2);
//		return list;
//	}

}
