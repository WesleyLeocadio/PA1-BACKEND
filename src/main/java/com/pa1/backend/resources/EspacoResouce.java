package com.pa1.backend.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/espacos") //vai responder por este endPoint
public class EspacoResouce {
	//pra ser uma função REST é preciso associar com algum dos verbos do http
	@RequestMapping(method = RequestMethod.GET)//como tô  obtendo dados uso o metodo get
	public String listar() {
		return "Rest está funcionando";
	}

}
