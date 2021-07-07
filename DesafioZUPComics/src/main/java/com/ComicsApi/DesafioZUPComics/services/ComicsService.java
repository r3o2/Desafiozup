package com.ComicsApi.DesafioZUPComics.services;

import com.ComicsApi.DesafioZUPComics.dto.CadastroComicDto;
import com.ComicsApi.DesafioZUPComics.dto.ComicDto;
import com.ComicsApi.DesafioZUPComics.dto.UsuarioDto;
import com.ComicsApi.DesafioZUPComics.entities.Comic;
import com.ComicsApi.DesafioZUPComics.entities.Usuario;
import com.ComicsApi.DesafioZUPComics.repositories.ComicRepository;
import com.ComicsApi.DesafioZUPComics.repositories.UsuarioRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ComicsService {

    @Autowired
    ComicRepository comicRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    
    public Comic cadastro(CadastroComicDto cadastroComicDto) {

        Object objetoComic = getComic(cadastroComicDto.getComicId());
        if (objetoComic == null) {
            return null;
        }

        Gson gson = new Gson();
        String comicResponseString = gson.toJson(objetoComic, LinkedHashMap.class);
        JSONObject comicResponseObject = gson.fromJson(comicResponseString, JSONObject.class);
        
        String comicDataString = gson.toJson(comicResponseObject.get("data"));
        JSONObject comicResultsObject = gson.fromJson(comicDataString, JSONObject.class);
        
        JsonArray resultList = new Gson().toJsonTree(comicResultsObject.get("results")).getAsJsonArray();
        String comicInfoString = gson.toJson(resultList.get(0));
        JSONObject comicInfoObject = gson.fromJson(comicInfoString, JSONObject.class);
        
        JsonArray precoLista = new Gson().toJsonTree(comicInfoObject.get("prices")).getAsJsonArray();
        String stringPreco = gson.toJson(precoLista.get(0));
        JSONObject objetoPreco = gson.fromJson(stringPreco, JSONObject.class);
        
        String creatorsSection = gson.toJson(comicInfoObject.get("creators"));
        JSONObject creatorsSectionObject = gson.fromJson(creatorsSection, JSONObject.class);
        
        JsonArray creatorsLista = new Gson().toJsonTree(creatorsSectionObject.get("items")).getAsJsonArray();

        String titulo = gson.toJson(comicInfoObject.get("title"));
        String descricao = gson.toJson(comicInfoObject.get("description"));
        String isbn = gson.toJson(comicInfoObject.get("isbn"));
        String preco = gson.toJson(objetoPreco.get("price"));
        StringBuilder autoresBuilder = new StringBuilder();
        for (JsonElement criador : creatorsLista) {
            String stringCriador = gson.toJson(criador);
            JSONObject objetoCriador = gson.fromJson(stringCriador, JSONObject.class);
            String nomeCriador = gson.toJson(objetoCriador.get("name"));

            autoresBuilder.append(nomeCriador).append(", ");
        }

        String autores;

        
        if (autoresBuilder.toString().equals("")) {
            autores = null;
        } else {
            autores = autoresBuilder.toString().replace("\"", "");
        }

     
        Comic novaComic = new Comic();
        novaComic.setComicId(cadastroComicDto.getComicId());
        novaComic.setUsuario(usuarioRepository.getById(cadastroComicDto.getIdUsuario()));
        novaComic.setTitulo(titulo.replace("\"", ""));
        novaComic.setDescricao(descricao.replace("\"", "").replace("\\", ""));

      
        if (isbn.equals("\"\"")) {
            novaComic.setIsbn(null);
        } else {
            novaComic.setIsbn(isbn.replace("\"", ""));
        }

        //Retornando preço
        if (Double.parseDouble(preco) == 0.0) {
            novaComic.setPreco(BigDecimal.valueOf(0.0));
        } else {
            novaComic.setPreco(BigDecimal.valueOf(Double.parseDouble(preco)));
        }

        novaComic.setAutores(autores);

        try {
            comicRepository.save(novaComic);

        } catch (DataIntegrityViolationException ex) {
            return null;
        }

        return comicRepository.getById(novaComic.getComicId());
    }


 
    public UsuarioDto listarComics(Long id) {
        try {
            Usuario usuario = usuarioRepository.getById(id);
            List<Comic> listaComics = comicRepository.findAllByUsuario(usuarioRepository.getById(id));
            UsuarioDto usuarioFinal = new UsuarioDto();
            List<ComicDto> listaComicsFinal = new ArrayList<>();

            for (Comic comic : listaComics) {
                ComicDto comicFinal = new ComicDto();
                comicFinal.setComicId(comic.getComicId());
                comicFinal.setAutores(comic.getAutores());
                comicFinal.setTitulo(comic.getTitulo());
                comicFinal.setDescricao(comic.getDescricao());
                
                comicFinal.setIsbn(comic.getIsbn());
                
                
                comicFinal.setDiaDesconto(diaDesconto(comic.getIsbn()));
                comicFinal.setDescontoAtivo(descontoAtivo(comicFinal.getDiaDesconto()));
                if (comicFinal.getDescontoAtivo()) {
                    comicFinal.setPreco(desconto(comic.getPreco()));
                } else {
                    comicFinal.setPreco(comic.getPreco());
                }
                listaComicsFinal.add(comicFinal);
            }
            usuarioFinal.setNome(usuario.getNome());
            usuarioFinal.setEmail(usuario.getEmail());
            usuarioFinal.setCpf(usuario.getCpf());
            usuarioFinal.setNascimento(usuario.getNascimento());
            usuarioFinal.setComics(listaComicsFinal);
            return usuarioFinal;
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

   
    public String diaDesconto(String isbn) {

        String digitoFinalAux;
        if (isbn == null) digitoFinalAux = "0";
        else digitoFinalAux = isbn.substring(isbn.length() - 1);

        if (digitoFinalAux.equals("X")) digitoFinalAux = "9";

        int digitoFinal = Integer.parseInt(digitoFinalAux);

        if (digitoFinal == 0 || digitoFinal == 1) {
            return "Monday";
        } else if (digitoFinal == 2 || digitoFinal == 3) {
            return "Tuesday";
        } else if (digitoFinal == 4 || digitoFinal == 5) {
            return "Wednesday";
        } else if (digitoFinal == 6 || digitoFinal == 7) {
            return "Thursday";
        } else {
            return "Friday";
        }
    }

  
    public Boolean descontoAtivo(String diaDesconto) {
        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
        return diaDesconto.toUpperCase().equals(dayOfWeek.toString());

    }


    public BigDecimal desconto(BigDecimal preco) {
        int porcentagemDesconto = 10;
        float precoFloat = preco.floatValue();
        BigDecimal novoPreco = BigDecimal.valueOf(precoFloat - precoFloat * (porcentagemDesconto / 100.00));
        return novoPreco.setScale(2, RoundingMode.HALF_UP);
    }


   
    public String geraUrl(Long id) {
        String publicKey = "SuaPublicKey";
        String privateKey = "SuaPrivatekey";
        Long timeStamp = System.currentTimeMillis();
        byte[] hash = org.apache.commons.codec.digest.DigestUtils.md5(timeStamp + privateKey + publicKey);
        String result = new String(Hex.encodeHex(hash));
        return "https://gateway.marvel.com/v1/public/comics/" + id + "?ts=" + timeStamp + "&apikey=" + publicKey + "&hash=" + result;
    }

   
    public Object getComic(Long comicId) {
        String urlCompleta = geraUrl(comicId);
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(urlCompleta)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (WebClientResponseException ex) {
            return null;
        }
    }


}


