package com.pa1.backend.dto;

import java.io.Serializable;

public class EspacoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String espacoNome;
    private String espacoDescricao;
    private String espacoLocalizacao;
    private boolean espacoEspecial;
    private String espacoResponsavel;
    private boolean espacoDesabilitado;

    public EspacoDTO() {

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

    public boolean isEspacoEspecial() {
        return espacoEspecial;
    }

    public void setEspacoEspecial(boolean espacoEspecial) {
        this.espacoEspecial = espacoEspecial;
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

}
