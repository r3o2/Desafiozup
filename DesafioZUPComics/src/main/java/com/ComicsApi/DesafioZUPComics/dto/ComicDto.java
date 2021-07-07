package com.ComicsApi.DesafioZUPComics.dto;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ComicDto implements Serializable {

	private Long ComicId;
	private String titulo;
	private String descricao;
	private String isbn;
	private String autores;
	private BigDecimal preco;
	private String diaDesconto;
	private Boolean descontoAtivo;

	// Getters & Setters

	public String getTitulo() {
		return titulo;
	}

	public Long getComicId() {
		return ComicId;
	}

	public void setComicId(Long comicId) {
		ComicId = comicId;
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

	public String getDiaDesconto() {
		return diaDesconto;
	}

	public void setDiaDesconto(String diaDesconto) {
		this.diaDesconto = diaDesconto;
	}

	public Boolean getDescontoAtivo() {
		return descontoAtivo;
	}

	public void setDescontoAtivo(Boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}
}
