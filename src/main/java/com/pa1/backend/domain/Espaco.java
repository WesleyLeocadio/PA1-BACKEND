package com.pa1.backend.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

//implementando a interface serializable( os objetos sao convertidos em uma sequencia de bytes) pra ser gravados em arquivos, trafegar em rede...
@Entity
public class Espaco implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEspaco;
	private String espacoNome;
	private String espacoDescricao;
    private String espacoLocalizacao;
    private boolean espacoEspecial;
    private String espacoResponsavel;
    private boolean espacoDesabilitado;
    
    public Espaco() {}

	public Espaco(int idEspaco, String espacoNome, String espacoDescricao, String espacoLocalizacao,
			boolean espacoEspecial, String espacoResponsavel, boolean espacoDesabilitado) {
		super();
		this.idEspaco = idEspaco;
		this.espacoNome = espacoNome;
		this.espacoDescricao = espacoDescricao;
		this.espacoLocalizacao = espacoLocalizacao;
		this.espacoEspecial = espacoEspecial;
		this.espacoResponsavel = espacoResponsavel;
		this.espacoDesabilitado = espacoDesabilitado;
	}

	public int getIdEspaco() {
		return idEspaco;
	}

	public void setIdEspaco(int idEspaco) {
		this.idEspaco = idEspaco;
	}

	public String getEspacoNome() {
		return espacoNome;
	}

	public void setEspacoNome(String espacoNome) {
		this.espacoNome = espacoNome;
	}

	public String getEspacoDescricao() {
		return espacoDescricao;
	}

	public void setEspacoDescricao(String espacoDescricao) {
		this.espacoDescricao = espacoDescricao;
	}

	public String getEspacoLocalizacao() {
		return espacoLocalizacao;
	}

	public void setEspacoLocalizacao(String espacoLocalizacao) {
		this.espacoLocalizacao = espacoLocalizacao;
	}

	public boolean isEspacoEsopecial() {
		return espacoEspecial;
	}

	public void setEspacoEsopecial(boolean espacoEsopecial) {
		this.espacoEspecial = espacoEsopecial;
	}

	public String getEspacoResponsavel() {
		return espacoResponsavel;
	}

	public void setEspacoResponsavel(String espacoResponsavel) {
		this.espacoResponsavel = espacoResponsavel;
	}

	public boolean isEspacoDesabilitado() {
		return espacoDesabilitado;
	}

	public void setEspacoDesabilitado(boolean espacoDesabilitado) {
		this.espacoDesabilitado = espacoDesabilitado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEspaco;
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
		if (idEspaco != other.idEspaco)
			return false;
		return true;
	}
    
    //Para que os objetos seram comparados pelos seus conteudos (nao pelos ponteiros de memoria) tem que implementar os metodos hash e equals
	
    

}
