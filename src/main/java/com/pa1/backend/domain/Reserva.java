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
	private Integer id;
	private Date dataReserva;
	private String justificativa;
	private Integer horarios[] = new Integer[16];
	private Integer diaSemana[] = new Integer[7];
	private Boolean aprovada;
	private Boolean cancelada;

    //@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="espaco_id")
	private Espaco espaco;

	//@JsonManagedReference
    @ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Reserva() {}

	public Reserva(Integer id, Date data, String justificativa, Integer[] horarios, Integer[] diaSemana, Boolean aprovada, Boolean cancelada, Espaco espaco, Usuario usuario) {
		this.id = id;
		this.dataReserva = data;
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

	public Date getData() {
		return dataReserva;
	}

	public void setData(Date data) {
		this.dataReserva = data;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Integer[] getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Integer[] diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Boolean getAprovada() {
		return aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
