package com.pa1.backend;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.domain.Usuario;
import com.pa1.backend.repositories.EspacoRepository;
import com.pa1.backend.repositories.ReservaRepository;
import com.pa1.backend.repositories.UsuarioRepository;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		
		
		
	}

}
