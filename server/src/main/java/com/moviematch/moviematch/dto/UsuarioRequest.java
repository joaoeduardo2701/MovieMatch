package com.moviematch.moviematch.dto;

import com.moviematch.moviematch.enums.Genero;
import lombok.Data;
import java.util.List;

@Data
public class UsuarioRequest {
    private String nome;
    private String email;
    private List<Genero> generosFavoritos;
    private List<Long> filmesAssistidosIds; // apenas os IDs dos filmes
}
