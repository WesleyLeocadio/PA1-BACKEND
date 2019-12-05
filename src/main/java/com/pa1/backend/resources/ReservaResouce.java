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

import com.pa1.backend.dto.ReservaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;
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
            @RequestParam @DateTimeFormat(pattern="dd-MM-yyyy")  String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Reserva> list= service.findByDate(d);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation("Listar Reservas pela data de um Espaço")
    @RequestMapping(path = {"/dateEspaco"},method = RequestMethod.GET)
    public ResponseEntity<List<Reserva>> findByDateEspaco(
            @ApiParam("Id do Espaço")
            @RequestParam Integer id,
            @ApiParam("Data  no formato dd-MM-yyyy")
            @DateTimeFormat(pattern="dd-MM-yyyy")  String date

    ){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();
        Date d2 = new Date();
        try {
            d = sdf.parse(date);
            d2 =sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Reserva> list= service.findByDateEspaco(id, d, d2);
        return ResponseEntity.ok().body(list);
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


    @ApiOperation("Listar Reservas Canceladas")
    @RequestMapping(path = {"/canceladas"},method = RequestMethod.GET)
    public ResponseEntity<List<Reserva>> findByCanceladas(){
        List<Reserva> list= service.findByCanceladas();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation("Cancelar Reserva")
    @RequestMapping(path = {"/cancelar"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> cancelaReserva(
            @ApiParam("Id da Reserva")
            @RequestParam Integer id,
            @ApiParam("Justificativa")
            @RequestParam String justificativa
    ){
        Reserva obj = service.buscar(id);
        obj.setJustificativa(justificativa);
        obj.setCancelada(true);
        service.update(obj);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Aprovar Reserva")
    @RequestMapping(path = {"/aprovar"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> aprovaReserva(
            @ApiParam("Id da Reserva")
            @RequestParam Integer id
    ){
        Reserva obj = service.buscar(id);
        obj.setAprovada(true);
        service.update(obj);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Cadastrar Reserva")
    @RequestMapping(method = RequestMethod.POST)
    public  ResponseEntity<Void> insertReserva(
            @ApiParam("Objeto de Reserva")
            @Valid @RequestBody ReservaDTO objDto
    ) {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-
        //Implementação
        Reserva obj = new Reserva();

        try {
            obj = service.fromDTO(objDto);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!detectaColisao(obj, obj.getDataInicio(), obj.getDataFim(), obj.getDiaSemana())) {
                service.insert(obj);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(obj.getId())
                        .toUri();
                return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.noContent().build();
    }
        /*try {
            obj = service.fromDTO(objDto);
            service.insert(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();*/

    @ApiOperation("Editar Reserva")
    @RequestMapping(path = {"/update"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateReserva(
            @ApiParam("Id da Reserva")
            @RequestParam Integer id,
            @ApiParam("Data da Reserva no formato dd-MM-yyyy")
            @DateTimeFormat(pattern="dd-MM-yyyy") Date dataInicio,
            @DateTimeFormat(pattern="dd-MM-yyyy") Date dataFim
    ){
        //serviço editando apenas uma reserva por vez
        Reserva obj = service.buscar(id);
        Integer[] diaSemana = recuperaDia(dataInicio);

        if(!detectaColisao(obj, dataInicio, dataFim, diaSemana)){
            obj.setDataInicio(dataInicio);
            obj.setDataFim(dataFim);
            obj.setDiaSemana(diaSemana);
            service.update(obj);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    private boolean detectaColisao(Reserva obj, Date dataInicio, Date dataFim, Integer[] diaSemana){
        List<Date> listaDatas = new ArrayList<>();
        listaDatas = determinarDatas(dataInicio, dataFim, diaSemana);

        for(int i =0 ;i <listaDatas.size();i++){
            List<Reserva> list = service.findByReservaDateEspaco(obj.getEspaco().getId(), listaDatas.get(i), dataFim);
            if(!list.isEmpty()){
                for (Reserva reserva : list) {
                    for (int j = 0; j < reserva.getHorarios().length; j++) {
                        Integer horariosobj[] = obj.getHorarios();
                        Integer horariosReserva[] = reserva.getHorarios();
                        if (horariosobj[j] == 1 && horariosReserva[j] == 1) {
                            return true;
                        }
                    }
                }
            }else{
                return false;
            }
        }
        return false;
    }

    private List determinarDatas(Date inicio, Date fim, Integer[] diaSemana){
        List<Date> listaDatas = new ArrayList<Date>();
        int dia = 0;

        for (int i = 0; i <diaSemana.length; i++){
            if(diaSemana[i]==1){
                dia = i+1;
                break;
            }
        }

        DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
        Date dt1 = inicio;
        Date dt2 = fim;
        Calendar cal = Calendar.getInstance();
        cal.setTime (dt1);
        Date dt;

        for (dt = dt1; dt.compareTo (dt2) <= 0; ) {

            if(Calendar.DAY_OF_WEEK == dia){
                listaDatas.add(dt);
            }
            System.out.println (df.format (dt));
            cal.add (Calendar.DATE, +1);
            dt = cal.getTime();
        }
        return listaDatas;
    }

    private Integer[] recuperaDia(Date dataInicio) {
        Integer[] diaSemana = {};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicio);
        int dia = cal.get(Calendar.DAY_OF_WEEK);
        if(dia == 1){
            diaSemana = new Integer[]{1, 0, 0, 0, 0, 0, 0};
        }else if(dia == 2){
            diaSemana = new Integer[]{0, 1, 0, 0, 0, 0, 0};
        }else if(dia == 3){
            diaSemana = new Integer[]{0, 0, 1, 0, 0, 0, 0};
        }else if(dia == 4){
            diaSemana = new Integer[]{0, 0, 0, 1, 0, 0, 0};
        }else if(dia == 5){
            diaSemana = new Integer[]{0, 0, 0, 0, 1, 0, 0};
        }else if(dia == 6){
            diaSemana = new Integer[]{0, 0, 0, 0, 0, 1, 0};
        }else if(dia == 7){
            diaSemana = new Integer[]{0, 0, 0, 0, 0, 0, 1};
        }
        return diaSemana;
    }
}