package com.ComicsApi.DesafioZUPComics.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class UsuarioDto implements Serializable {

    private String nome;
    private String email;
    private String cpf;
    private LocalDate nascimento;
    private List<ComicDto> comics;

    //Getters & Setters
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<ComicDto> getComics() {
        return comics;
    }

    public void setComics(List<ComicDto> comics) {
        this.comics = comics;
    }
}
