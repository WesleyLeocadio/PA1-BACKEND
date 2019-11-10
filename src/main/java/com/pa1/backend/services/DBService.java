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
		Usuario user4 = new Usuario(null, "Ana Bheatriz Chacon","anabheatrizchacon@outlook.com.com","00000000",3,pe.encode("biachacon"));
		Usuario user5 = new Usuario(null, "Tiago batista","tiagoBatista@gmail.com","99928989",2,pe.encode("tiago"));

		usuarioRepository.save(Arrays.asList(user1,user2,user3,user4,user5));

		Espaco esp1 = new Espaco(null,"Lab 2","Laboratório de informática","Prédio de Informática","Luiz Antônio",false,false);
		Espaco esp2 = new Espaco(null,"Lab 3","Sala de estudos","Prédio de Informática","Luiz Antônio",false,false);
		Espaco esp3 = new Espaco(null,"Auditório","Auditório do ensino médio da EAJ","Audtório possui caixa de som ...", "Luiz Antônio",true,false);
		Espaco esp4 = new Espaco(null,"Lab 4","Laboratório de química do ensino médio da EAJ","Laboratório de química","Luiz Fernando",true,false);
		Espaco esp5 = new Espaco(null,"Lab 5","Sala de aula","Capacidade para 25 alunos", "Luiz Fernando",false,false);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Integer horarios1[] = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer horarios2[] = {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer horarios3[] = {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0};
		Integer horarios4[] = {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0};
		Integer horarios5[] = {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0};

		Integer diaSemana1[] = {0,1,0,0,0,0,0};
		Integer diaSemana2[] = {0,0,0,1,0,0,0};
		Integer diaSemana3[] = {0,0,1,0,0,0,0};
		Integer diaSemana4[] = {0,0,0,1,0,0,0};
		Integer diaSemana5[] = {0,0,0,0,1,0,0};

		Reserva r1 = new Reserva(null,sdf.parse("15-09-2019"),"", horarios1, diaSemana1, true, false, esp1, user1);

		Reserva r2 = new Reserva(null,sdf.parse("26-12-2019"), "", horarios2, diaSemana2, false, false, esp2, user2);
		Reserva r3 = new Reserva(null,sdf.parse("02-12-2019"), "", horarios2, diaSemana2, false, false, esp2, user2);

		Reserva r4 = new Reserva(null,sdf.parse("24-12-2019"), "", horarios2, diaSemana3, false, false, esp5, user3);
		Reserva r5 = new Reserva(null,sdf.parse("25-12-2019"), "", horarios2, diaSemana4, false, false, esp5, user4);
		Reserva r6 = new Reserva(null,sdf.parse("26-12-2019"), "", horarios2, diaSemana5, false, false, esp5, user5);

		Reserva r7 = new Reserva(null,sdf.parse("22-12-2019"),"", horarios4, diaSemana1, true, false, esp1, user1);
		Reserva r8 = new Reserva(null,sdf.parse("29-12-2019"),"", horarios5, diaSemana1, true, false, esp1, user1);
		
		esp1.getReservas().addAll(Arrays.asList(r1));
		esp2.getReservas().addAll(Arrays.asList(r2));
		
		espacoRepository.save(Arrays.asList(esp1,esp2,esp3,esp4,esp5));
		reservaRepository.save(Arrays.asList(r1,r2,r3,r4,r5));

	}

}
