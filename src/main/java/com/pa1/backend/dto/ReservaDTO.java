package com.pa1.backend.dto;

import java.io.Serializable;
import java.util.Date;

import com.pa1.backend.domain.Espaco;

public class ReservaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date dataReserva;
	private Integer horarios[] = new Integer[16];
	private String responsavel;
	
	private Espaco espaco;

	public ReservaDTO() {
		
	}
	public Date getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Integer[] getHorarios() {
		return horarios;
	}
	public void setHorarios(Integer[] horarios) {
		this.horarios = horarios;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
	
	
    
    
    
    

}
