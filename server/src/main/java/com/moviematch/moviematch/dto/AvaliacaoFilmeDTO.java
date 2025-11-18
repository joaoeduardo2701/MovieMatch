package com.moviematch.moviematch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvaliacaoFilmeDTO {
    private String nomeUsuario;
    private int estrelas;
    private String comentario;
}
