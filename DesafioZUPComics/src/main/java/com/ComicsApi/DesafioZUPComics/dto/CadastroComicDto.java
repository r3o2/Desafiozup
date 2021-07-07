package com.ComicsApi.DesafioZUPComics.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CadastroComicDto implements Serializable {

    Long idUsuario;
    Long ComicId;

    //Getters & Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

	public Long getComicId() {
		return ComicId;
	}

	public void setComicId(Long comicId) {
		ComicId = comicId;
	}

   
}
