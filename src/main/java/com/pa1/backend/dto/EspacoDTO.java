package com.pa1.backend.dto;

import java.io.Serializable;

public class EspacoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private String localizacao;
    private String responsavel;
    private boolean especial;
    private boolean desabilitado;

    public EspacoDTO() {

    }

    public EspacoDTO(String nome, String descricao, String localizacao, String responsavel, boolean especial, boolean desabilitado) {
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.responsavel = responsavel;
        this.especial = especial;
        this.desabilitado = desabilitado;
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

}
