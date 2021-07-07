package com.ComicsApi.DesafioZUPComics.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @Column(name = "id_comic")
    private Long ComicId;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "titulo", length = 1024)
    private String titulo;

    @Column(name = "descricao", length = 1024)
    private String descricao;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "autores", length = 1024)
    private String autores;

    @Column(name = "preco")
    private BigDecimal preco;

    //Getters & Setters
   

    public Usuario getUsuario() {
        return usuario;
    }

    public Long getComicId() {
		return ComicId;
	}

	public void setComicId(Long comicId) {
		ComicId = comicId;
	}

	public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
