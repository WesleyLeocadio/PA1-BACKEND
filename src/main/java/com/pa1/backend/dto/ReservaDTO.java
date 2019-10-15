package com.pa1.backend.dto;

import java.io.Serializable;
import java.util.Date;

import com.pa1.backend.domain.Espaco;

public class ReservaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date dataReservaInicio;
	private Date dataReservaFim;
	private Integer horarios[] = new Integer[16];
	private String responsavel;
	private Espaco espaco;

	public ReservaDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataReservaInicio() {
		return dataReservaInicio;
	}

	public void setDataReservaInicio(Date dataReservaInicio) {
		this.dataReservaInicio = dataReservaInicio;
	}

	public Date getDataReservaFim() {
		return dataReservaFim;
	}

	public void setDataReservaFim(Date dataReservaFim) {
		this.dataReservaFim = dataReservaFim;
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
