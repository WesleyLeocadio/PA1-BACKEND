package com.pa1.backend.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.services.ReservaService;

//classe vai ser um controlador REST
@RestController
@RequestMapping(value = "/reservas")
public class ReservaResouce {

	@Autowired
	private ReservaService service;

	@ApiOperation("Buscar reserva pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(
			@ApiParam("Id do objeto cadastrado de Reserva")
			@PathVariable Integer id) {
		Reserva obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation("Listar todas as Reservas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findAll() {
		List<Reserva> list= service.findAll();
		return ResponseEntity.ok().body(list);

	}

	@ApiOperation("Listar Reservas de um Espaço")
	@RequestMapping(path = {"/espaco"}, method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findByEspaci(
			@ApiParam("Id do Espaco")
			@RequestParam Espaco espaco){
		List<Reserva> list = service.findByEspaco(espaco);
		return  ResponseEntity.ok().body(list);
	}

	@ApiOperation("Listar Reservas pela data")
	@RequestMapping(path = {"/date"},method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> findByDate(
			@ApiParam("Data")
			@RequestParam @DateTimeFormat(pattern="dd-MM-yyyy")  Date date) {
		List<Reserva> list= service.findByDate(date);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation("Cadastrar Reserva")
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<Void> insertReserva(
			@ApiParam("Objeto de Reserva para salvar no Banco de dados")
			@Valid @RequestBody ReservaDTO objDto ){
		Reserva obj = service.fromDTO(objDto);

		//verificar data no espaco
		if (!detectaColisao(obj)){
			System.out.println("NÃO TEM");
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReserva())
					.toUri();
			return ResponseEntity.created(uri).build();
		}else{
			System.out.println("JÁ TEM");
			return ResponseEntity.noContent().build();
		}

	}

	@ApiOperation("Cancelar Reserva de Terceiros")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarReserva(
			@ApiParam("Id da Reserva")
			@PathVariable Integer id
	){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	//Editar reserva
	//muda data e horario
	@ApiOperation("Editar Reserva")
	@RequestMapping(path = {"/update"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> updateReserva(
			@RequestParam Integer id,
			@DateTimeFormat(pattern="dd-MM-yyyy")  Date dateInicio,
			@DateTimeFormat(pattern="dd-MM-yyyy")  Date dataFim
			) {

		Reserva obj = service.buscar(id);

		if (!detectaColisaoUpdate(obj, dateInicio)) {
			obj.setDataReservaInicio(dateInicio);
			obj.setDataReservaFim(dataFim);
			service.update(obj);
			System.out.println("Alterou a data");
			return ResponseEntity.ok().build();

		}else{
			System.out.println("não alterou");
			return  ResponseEntity.noContent().build();
		}



	}
	private boolean detectaColisao(Reserva obj){
		//colisão de data fim igual final recorrente
		List<Reserva> list = service.findByReserva(obj.getEspaco().getIdEspaco(), obj.getDataReserva());

		if (list.isEmpty()){
			return false;
		}else{
			for(Reserva reserva:list){
				for (int i = 0; i<reserva.getHorarios().length ;i++){
					Integer horariosobj[] = obj.getHorarios();
					Integer horariosReserva[] = reserva.getHorarios();
					if(horariosobj[i]==1 && horariosReserva[i]==1){
						return true;
					}
				}
			}
			return false;
		}
		
	}

	private boolean detectaColisaoUpdate(Reserva obj, Date data){
		//colisão
		List<Reserva> list = service.findByReserva(obj.getEspaco().getIdEspaco(), data);

		if (list.isEmpty()){
			return false;
		}else{
			for(Reserva reserva:list){
				for (int i = 0; i<reserva.getHorarios().length ;i++){
					Integer horariosobj[] = obj.getHorarios();
					Integer horariosReserva[] = reserva.getHorarios();
					if(horariosobj[i]==1 && horariosReserva[i]==1){
						return true;
					}
				}
			}
			return false;
		}

	}



}
