package com.pa1.backend.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pa1.backend.domain.Espaco;
//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/espacos") //vai responder por este endPoint
public class EspacoResouce {
	//pra ser uma função REST é preciso associar com algum dos verbos do http
	@RequestMapping(method = RequestMethod.GET)//como tô  obtendo dados uso o metodo get
	public List<Espaco> listar() {
		Espaco esp1= new Espaco(1,"lab informatica","sala com computadores","predio de informatica",false,"Laura",false);
		Espaco esp2= new Espaco(2,"Sala teorica","sala comum","predio de informatica",false,"Taniro",false);
		List<Espaco> list=new ArrayList<Espaco>();
		list.add(esp1);
		list.add(esp2);
		return list;
	}

}
