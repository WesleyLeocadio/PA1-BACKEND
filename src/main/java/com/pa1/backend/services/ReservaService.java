package com.pa1.backend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.pa1.backend.resources.EspacoResouce;
import com.pa1.backend.resources.UsuarioResouce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.repositories.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repo;

	@Autowired
	private UsuarioResouce uso;

	@Autowired
	private EspacoResouce espaco;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public Reserva fromDTO(ReservaDTO objDto) throws ParseException {
		Reserva r1 = new Reserva(null,
				sdf.parse(objDto.getDataInicio()),
				sdf.parse(objDto.getDataFim()),
				objDto.getJustificativa(),
				objDto.getHorarios(),
				objDto.getDiaSemana(),
				objDto.getAprovada(),
				objDto.getCancelada(),
				espaco.findByIdTeste(objDto.getEspaco()),
				uso.findByIdTeste(objDto.getUsuario())
		);
		return r1;
	}

	public Reserva insert(Reserva obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Reserva update(Reserva obj){
		return  repo.save(obj);
	}

	public List<Reserva> findByDate(Date d){
		return repo.findByDate(d);
	}

	public List<Reserva> findByDateEspaco(Integer id, Date data){
		return repo.findByDateEspaco(id, data);
	}

	public List<Reserva> findAll() {
		return repo.findAll();
	}

	public List<Reserva> findByEspaco(Espaco e){
		return repo.findByEspaco(e);
	}

	public Reserva buscar(Integer id) {
		Reserva obj = repo.findOne(id);
		return obj;
	}

	public List<Reserva> findByCanceladas(){
		return repo.findByCanceladas();
	}

	public List<Reserva> findByAprovadas(){
		return repo.findByAprovadas();
	}

	public List<Reserva> findByPendentes(){
		return repo.findByPendentes();
	}

	public List<Reserva> findByReserva(Integer idEspaco, Date d){
		return repo.findByReserva(idEspaco,d);
	}

}
