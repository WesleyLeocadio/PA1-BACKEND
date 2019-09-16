package com.pa1.backend.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.services.EspacoService;
import com.pa1.backend.services.ReservaService;

//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/reservas") // vai responder por este endPoint
public class ReservaResouce {

	@Autowired
	private ReservaService service;

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findAll() {
		List<Reserva> list= service.findAll();
		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(path = {"/date"},method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findByDate(@RequestParam @DateTimeFormat(pattern="dd-MM-yyyy")  Date date) {
		List<Reserva> list= service.findByDate(date);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<Void> insert(@Valid @RequestBody ReservaDTO objDto ){
		Reserva obj= service.fromDTO(objDto);
		obj=service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReserva())
				.toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
 


}
