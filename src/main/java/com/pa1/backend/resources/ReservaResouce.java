package com.pa1.backend.resources;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
import com.pa1.backend.dto.ReservaDTO;
import com.pa1.backend.services.ReservaService;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaResouce {

    @Autowired
    private ReservaService service;

    @ApiOperation("Buscar Reserva pelo id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(
            @ApiParam("Id da Reserva")
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
            @ApiParam("Data no formato dd-MM-yyyy")
            @RequestParam @DateTimeFormat(pattern="dd-MM-yyyy")  Date date) {
        List<Reserva> list= service.findByDate(date);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation("Cadastrar Reserva")
    @RequestMapping(method = RequestMethod.POST)
    public  ResponseEntity<Void> insertReserva(
            @ApiParam("Objeto de Reserva")
            @Valid @RequestBody ReservaDTO objDto ){
        Reserva obj = service.fromDTO(objDto);
        //gera o intervalo de datas inicio até fim, se igual gera apenas uma data
        List<Date> todasDatas = determinarDatas(obj.getDataReservaInicio(), obj.getDataReservaFim());
        if (!detectaColisao(obj, todasDatas)){
            //se a data fim for maior do que a darta inicio o for roda todas as datas criando uma reserva para cada dia do intervalo
            for(int i = 0; i<todasDatas.size(); i++){
                System.out.println("NÃO TEM");
                obj.setDataReservaInicio(todasDatas.get(i));
                obj.setDataReservaFim(todasDatas.get(i));
                obj = service.insert(obj);
            }
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

    @ApiOperation("Listar Reservas Aprovadas")
    @RequestMapping(path = {"/aprovadas"},method = RequestMethod.GET)
    public ResponseEntity<List<Reserva>> findByAprovadas(){
        List<Reserva> list= service.findByAprovadas();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation("Listar Reservas Pendentes")
    @RequestMapping(path = {"/pendentes"},method = RequestMethod.GET)
    public ResponseEntity<List<Reserva>> findByPendentes(){
        List<Reserva> list= service.findByPendentes();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation("Aprovar Reserva")
    @RequestMapping(path = {"/aprovar"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> aprovarReserva(
            @ApiParam("Id da Reserva")
            @RequestParam Integer id
    ){
        Reserva obj = service.buscar(id);
        obj.setAprovada(true);
        service.update(obj);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Editar Reserva")
    @RequestMapping(path = {"/update"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateReserva(
            @ApiParam("Id da Reserva")
            @RequestParam Integer id,
            @ApiParam("Data de início da Reserva no formato dd-MM-yyyy")
            @DateTimeFormat(pattern="dd-MM-yyyy")  Date dateInicio,
            @ApiParam("Data de fim da Reserva no formato dd-MM-yyyy")
            @DateTimeFormat(pattern="dd-MM-yyyy")  Date dataFim
    ) {

        Reserva obj = service.buscar(id);
        //gera o intervalo de datas
        //só está funcionando para uma data, se igual gera apenas uma data
        List<Date> todasDatas = determinarDatas(dateInicio, dataFim);
        if (!detectaColisao(obj, todasDatas)) {
            //sujeitao: caso não haja colisão deletar as reservas anteriores - crir algum medoto - sujeitao
            //alterar a data da reserva de acordo com o intervalo de datas, só está funcinando quando a inicio é igual a data fim.
            for (int i =0 ; i<todasDatas.size();i++){
                obj.setDataReservaInicio(todasDatas.get(i));
                obj.setDataReservaFim(todasDatas.get(i));
                service.update(obj);
                System.out.println("Alterou a data");
            }
            return ResponseEntity.ok().build();
        }else{
            System.out.println("não alterou");
            return  ResponseEntity.noContent().build();
        }

    }

    private boolean detectaColisao(Reserva obj, List<Date> todasDatas){
        //List<Date> todasDatas = determinarDatas(obj.getDataReservaInicio(), obj.getDataReservaFim());
        for (int i=0; i < todasDatas.size(); i++) {
            System.out.println("testando colisao");
            List<Reserva> list = service.findByReserva(obj.getEspaco().getIdEspaco(), todasDatas.get(i));
            if (list.isEmpty()) {
                return false;
            } else {
                for (Reserva reserva : list) {
                    System.out.println("verificando horarios");
                    for (int j = 0; j < reserva.getHorarios().length; j++) {
                        Integer horariosobj[] = obj.getHorarios();
                        Integer horariosReserva[] = reserva.getHorarios();
                        if (horariosobj[j] == 1 && horariosReserva[j] == 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private List determinarDatas(Date inicio, Date fim){
        List<Date> listaDatas = new ArrayList<Date>();
        DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
        Date dt1 = inicio;
        Date dt2 = fim;
        Calendar cal = Calendar.getInstance();
        cal.setTime (dt1);
        Date dt;
        for (dt = dt1; dt.compareTo (dt2) <= 0; ) {
            listaDatas.add(dt);
            System.out.println (df.format (dt));
            cal.add (Calendar.DATE, +1);
            dt = cal.getTime();
        }
        return listaDatas;
    }

}