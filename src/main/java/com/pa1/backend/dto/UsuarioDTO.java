package com.pa1.backend.dto;

import java.io.Serializable;

import com.pa1.backend.domain.Usuario;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
    private String telefone;
    
    public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		nome = obj.getNome();
		email = obj.getEmail();
		telefone=obj.getTelefone();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
   
    
    

}
