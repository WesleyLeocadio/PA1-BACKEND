package com.pa1.backend.dto;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Usuario;
import java.io.Serializable;

public class ReservaDTO  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id =null;
	private String dataReserva;
	private String justificativa;
	private Integer horarios[] = new Integer[12];
	private Integer diaSemana[] = new Integer[7];
	private Boolean aprovada;
	private Boolean cancelada;
	private Espaco espaco;
	private Usuario usuario;

	public ReservaDTO() {
		
	}

	public ReservaDTO(String dataReserva, String justificativa, Integer[] horarios, Integer[] diaSemana, Boolean aprovada, Boolean cancelada, Espaco espaco, Usuario usuario) {
		this.dataReserva = dataReserva;
		this.justificativa = justificativa;
		this.horarios = horarios;
		this.diaSemana = diaSemana;
		this.aprovada = aprovada;
		this.cancelada = cancelada;
		this.espaco = espaco;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(String dataReserva) {
		this.dataReserva = dataReserva;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Integer[] getHorarios() {
		return horarios;
	}

	public void setHorarios(Integer[] horarios) {
		this.horarios = horarios;
	}

	public Integer[] getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Integer[] diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Boolean getAprovada() {
		return aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
