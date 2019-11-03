package com.pa1.backend.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Perfil;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.domain.Usuario;
import com.pa1.backend.repositories.EspacoRepository;
import com.pa1.backend.repositories.ReservaRepository;
import com.pa1.backend.repositories.UsuarioRepository;
import java.text.ParseException;




@Service
public class DBService {
	
	
	@Autowired
	private EspacoRepository espacoRepository;
	@Autowired
	private ReservaRepository reservaRepository; 
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		Usuario user1 = new Usuario(null, "Luiz fernando","luizFermando@gmail.com","99928989",1,pe.encode("admin"));
		Usuario user2 = new Usuario(null, "Laura Emmanuella","lauraEmmanuella@gmail.com","99928989",2,pe.encode("laura"));
		Usuario user3 = new Usuario(null, "Weslley Leocadio","silvawesley@gmail.com","99928989",3,pe.encode("wesley"));

		//usuarioRepository.save(Arrays.asList(user1,user2,user3));
		
		
		Espaco esp1 = new Espaco(null,"Lab 2","Laboratório de informática","Prédio de Informática",false,"Luiz Antônio",false);
		Espaco esp2 = new Espaco(null,"Lab 3","Sala de estudos","Prédio de Informática",false,"Luiz Antônio",false);
		Espaco esp3 = new Espaco(null,"Auditório","Auditório do ensino médio da EAJ","Audtório possui caixa de som ...",true,"Luiz Antônio",false);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		 Integer horarios[] = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

		Reserva r1 = new Reserva(null,sdf.parse("30-09-2019"),sdf.parse("30-10-2019"),horarios,esp1,user1);
		Reserva r2 = new Reserva(null,sdf.parse("20-09-2019"),sdf.parse("30-09-2019"),horarios,esp2,user2);
		
		
		
		esp1.getReservas().addAll(Arrays.asList(r1));
		esp2.getReservas().addAll(Arrays.asList(r2));
		
		//espacoRepository.save(Arrays.asList(esp1,esp2,esp3));
		//reservaRepository.save(Arrays.asList(r1,r2));
		
		
		
	}
}
