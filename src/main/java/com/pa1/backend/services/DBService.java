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
		Usuario user4 = new Usuario(null, "Bia Chacon","biaChacon@gmail.com","00000000",3,pe.encode("biachacon"));
		Usuario user5 = new Usuario(null, "João Paulo","silvawesley@gmail.com","99928989",3,pe.encode("joao"));
		Usuario user6 = new Usuario(null, "Tiago batista","tiagoBatista@gmail.com","99928989",2,pe.encode("tiago"));
		Usuario user7 = new Usuario(null, "Francisco","francisco@gmail.com","99928989",2,pe.encode("francisco"));

		usuarioRepository.save(Arrays.asList(user1,user2,user3,user4,user5,user6,user7));
		
		
		Espaco esp1 = new Espaco(null,"Lab 2","Laboratório de informática","Prédio de Informática",false,"Luiz Antônio",false);
		Espaco esp2 = new Espaco(null,"Lab 3","Sala de estudos","Prédio de Informática",false,"Luiz Antônio",false);
		Espaco esp3 = new Espaco(null,"Auditório","Auditório do ensino médio da EAJ","Audtório possui caixa de som ...",true,"Luiz Antônio",false);
		Espaco esp4 = new Espaco(null,"Lab 4","Laboratório de química do ensino médio da EAJ","Laboratório de química",true,"Luiz Fernando",false);
		Espaco esp5 = new Espaco(null,"Lab 5","Sala de aula","Capacidade para 25 alunos",false,"Luiz Fernando",false);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Integer horarios1[] = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer horarios2[] = {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer horarios3[] = {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0};


		Reserva r1 = new Reserva(null,sdf.parse("30-09-2019"),sdf.parse("30-10-2019"),horarios1,esp1,user1, false, false);
		Reserva r2 = new Reserva(null,sdf.parse("20-09-2019"),sdf.parse("30-09-2019"),horarios1,esp2,user2, true, false);
		Reserva r3 = new Reserva(null,sdf.parse("10-09-2019"),sdf.parse("30-09-2019"),horarios2,esp2,user2, true, false);
		Reserva r4 = new Reserva(null,sdf.parse("11-09-2019"),sdf.parse("11-09-2019"),horarios2,esp5,user4, false, false);
		Reserva r5 = new Reserva(null,sdf.parse("12-09-2019"),sdf.parse("12-09-2019"),horarios3,esp5,user5, false, false);
		Reserva r6 = new Reserva(null,sdf.parse("13-09-2019"),sdf.parse("13-09-2019"),horarios3,esp5,user6, false, false);
		Reserva r7 = new Reserva(null,sdf.parse("14-09-2019"),sdf.parse("14-09-2019"),horarios3,esp4,user7, true, true);

		
		
		esp1.getReservas().addAll(Arrays.asList(r1));
		esp2.getReservas().addAll(Arrays.asList(r2));
		
		espacoRepository.save(Arrays.asList(esp1,esp2,esp3,esp4,esp5));
		reservaRepository.save(Arrays.asList(r1,r2,r3,r4,r5,r6,r7));

	}

}
