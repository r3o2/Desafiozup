package com.ComicsApi.DesafioZUPComics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ComicsApi.DesafioZUPComics.entities.Comic;
import com.ComicsApi.DesafioZUPComics.entities.Usuario;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    List<Comic> findAllByUsuario(Usuario usuario);
}
