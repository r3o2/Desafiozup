package com.ComicsApi.DesafioZUPComics.services;

import com.ComicsApi.DesafioZUPComics.dto.CadastroUsuarioDto;
import com.ComicsApi.DesafioZUPComics.entities.Usuario;
import com.ComicsApi.DesafioZUPComics.repositories.UsuarioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;
    
    public Usuario cadastro(CadastroUsuarioDto usuario) {
        try {
            Usuario novoUsuario = new Usuario();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(usuario.getNascimento(), formatter);

            novoUsuario.setNome(usuario.getNome());
            novoUsuario.setCpf(usuario.getCpf());
            novoUsuario.setEmail(usuario.getEmail());
            novoUsuario.setNascimento(date);

            return usuarioRepository.save(novoUsuario);
        } catch (DataIntegrityViolationException ex) {
            return null;
        }
    }
    
    public Optional<Usuario> consultarUsuario(long id) {
		return usuarioRepository.findById(id);
	}

	   
   
}
