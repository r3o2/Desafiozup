package com.ComicsApi.DesafioZUPComics.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseError {
    private Integer status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<String> erros;


    public ResponseError(Integer status, String titulo, LocalDateTime dataHora, List<String> erros) {
        super();
        this.status = status;
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.erros = erros;
    }


    //Getters & Setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
