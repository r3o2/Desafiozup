package com.ComicsApi.DesafioZUPComics.controllers;

import com.ComicsApi.DesafioZUPComics.dto.CadastroUsuarioDto;
import com.ComicsApi.DesafioZUPComics.entities.Usuario;

import com.ComicsApi.DesafioZUPComics.responses.ResponseHandler;
import com.ComicsApi.DesafioZUPComics.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastro")
	public ResponseEntity<Object> cadastroUsuario(@Valid @RequestBody CadastroUsuarioDto usuario) {

		Usuario novoUsuario = usuarioService.cadastro(usuario);

		if (novoUsuario == null) {
			return ResponseHandler.generateResponse("Já existe um usuário com esses dados cadastrado",
					HttpStatus.BAD_REQUEST, usuario);
		} else {
			return ResponseHandler.generateResponse("Efetuado o cadastro com sucesso!", HttpStatus.CREATED,
					novoUsuario);
		}
	}

	@GetMapping(path = "/{id}")
	public Optional<Usuario> consultarUsuario(@PathVariable("id") long id) {
		return usuarioService.consultarUsuario(id);

	}

}
