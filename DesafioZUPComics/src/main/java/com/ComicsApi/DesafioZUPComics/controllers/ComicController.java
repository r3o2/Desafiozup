package com.ComicsApi.DesafioZUPComics.controllers;

import com.ComicsApi.DesafioZUPComics.dto.CadastroComicDto;
import com.ComicsApi.DesafioZUPComics.dto.UsuarioDto;
import com.ComicsApi.DesafioZUPComics.entities.Comic;
import com.ComicsApi.DesafioZUPComics.responses.ResponseHandler;
import com.ComicsApi.DesafioZUPComics.services.ComicsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private ComicsService comicsService;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastroComic(@RequestBody CadastroComicDto cadastroComicDto) {
        Comic novaComic = comicsService.cadastro(cadastroComicDto);
        if (novaComic == null) {
            return ResponseHandler.generateResponse("Erro no Cadastro, Usuário ou Comic não encontrado. Confira os campos",
                    HttpStatus.BAD_REQUEST, cadastroComicDto);
        } else {
            return ResponseHandler.generateResponse("Cadastro realizado com sucesso!",
                    HttpStatus.CREATED, novaComic);
        }
    }

    @GetMapping("/lista/{idUsuario}")
    public ResponseEntity<Object> listaComics(@PathVariable Long idUsuario) {
        UsuarioDto usuarioDto = comicsService.listarComics(idUsuario);
        if (usuarioDto == null) {
            return ResponseHandler.generateResponse("Usuário não encontrado, confira o campo", HttpStatus.BAD_REQUEST,
                    ("idUsuario: " + idUsuario));
        } else {
            return ResponseHandler.generateResponse("Usuário encontrado com sucesso!", HttpStatus.OK,
                    usuarioDto);
        }
    }


}
