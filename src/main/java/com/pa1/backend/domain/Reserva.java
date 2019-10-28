package com.pa1.backend.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReserva;
	private Date dataReservaInicio;
	private Date dataReservaFim;
	private Integer horarios[] = new Integer[16];
	
    @JsonManagedReference
	@ManyToOne
	@JoinColumn(name="espaco_id")
	private Espaco espaco;
    
    @JsonBackReference
    @ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Reserva() {}

	public Reserva(Integer idReserva, Date dataReservaInicio, Date dataReservaFim, Integer[] horarios, Espaco espaco, Usuario user) {
		this.idReserva = idReserva;
		this.dataReservaInicio = dataReservaInicio;
		this.dataReservaFim = dataReservaFim;
		this.horarios = horarios;
		this.espaco = espaco;
		this.usuario=user;
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

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Date getDataReserva() {
		return dataReservaInicio;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReservaInicio = dataReserva;
	}

	public Integer[] getHorarios() {
		return horarios;
	}

	public void setHorarios(Integer[] horarios) {
		this.horarios = horarios;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idReserva == null) ? 0 : idReserva.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (idReserva == null) {
			if (other.idReserva != null)
				return false;
		} else if (!idReserva.equals(other.idReserva))
			return false;
		return true;
	}
	
	
	
}
