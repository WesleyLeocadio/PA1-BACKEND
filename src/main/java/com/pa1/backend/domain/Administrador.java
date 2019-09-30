package com.pa1.backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdministrador;
	private String administradorNome;
	private String administradorEmail;
    private String administradorTelefone;
    
   // private List<Reserva> enderecos = new ArrayList<Reserva>();
    
    public Administrador() {	
	}

	public Administrador(Integer idAdministrador, String administradorNome, String administradorEmail,
			String administradorTelefone) {
		super();
		this.idAdministrador = idAdministrador;
		this.administradorNome = administradorNome;
		this.administradorEmail = administradorEmail;
		this.administradorTelefone = administradorTelefone;
	}

	public Integer getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Integer idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getAdministradorNome() {
		return administradorNome;
	}

	public void setAdministradorNome(String administradorNome) {
		this.administradorNome = administradorNome;
	}

	public String getAdministradorEmail() {
		return administradorEmail;
	}

	public void setAdministradorEmail(String administradorEmail) {
		this.administradorEmail = administradorEmail;
	}

	public String getAdministradorTelefone() {
		return administradorTelefone;
	}

	public void setAdministradorTelefone(String administradorTelefone) {
		this.administradorTelefone = administradorTelefone;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAdministrador == null) ? 0 : idAdministrador.hashCode());
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
		Administrador other = (Administrador) obj;
		if (idAdministrador == null) {
			if (other.idAdministrador != null)
				return false;
		} else if (!idAdministrador.equals(other.idAdministrador))
			return false;
		return true;
	}

//	public List<Reserva> getEnderecos() {
//		return enderecos;
//	}
//
//	public void setEnderecos(List<Reserva> enderecos) {
//		this.enderecos = enderecos;
//	}
//    
    

}
