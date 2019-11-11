package com.pa1.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Espaco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private String localizacao;
	private String responsavel;
	private boolean especial;
	private String justificativa;
	private boolean desabilitado;
	private boolean computadores;
	private Integer qtdPessoas;
	private Integer ramal;

	@JsonBackReference
	@OneToMany(mappedBy = "espaco")
	private List<Reserva> reservas = new ArrayList<>();

	public Espaco() {

	}

	public Espaco(Integer id, String nome, String descricao, String localizacao, String responsavel, boolean especial, String justificativa, boolean desabilitado, boolean computadores, Integer qtdPessoas, Integer ramal) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.responsavel = responsavel;
		this.especial = especial;
		this.justificativa = justificativa;
		this.desabilitado = desabilitado;
		this.computadores = computadores;
		this.qtdPessoas = qtdPessoas;
		this.ramal = ramal;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public boolean isComputadores() {
		return computadores;
	}

	public void setComputadores(boolean computadores) {
		this.computadores = computadores;
	}

	public Integer getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(Integer qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Integer getRamal() {
		return ramal;
	}

	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
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
		Espaco other = (Espaco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}