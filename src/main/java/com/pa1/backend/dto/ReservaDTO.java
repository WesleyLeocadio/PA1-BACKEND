package com.pa1.backend.dto;

import java.io.Serializable;
import java.util.Date;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Usuario;

public class ReservaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date dataReservaInicio;
	private Date dataReservaFim;
	private Integer horarios[] = new Integer[16];
	private Usuario usuario;
	private Boolean aprovada;
	
	private Espaco espaco;

	public ReservaDTO() {
		
	}

	public ReservaDTO(Integer id, Date dataReservaInicio, Date dataReservaFim, Integer[] horarios, Espaco espaco, Usuario usuario,Boolean aprovada) {
		this.id = id;
		this.dataReservaInicio = dataReservaInicio;
		this.dataReservaFim = dataReservaFim;
		this.horarios = horarios;
		this.aprovada = aprovada;
		this.usuario = usuario;
		this.espaco = espaco;
	}

	public Boolean getAprovada() {
		return aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}

}
