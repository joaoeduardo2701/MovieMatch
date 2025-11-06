package com.moviematch.moviematch.model;

import com.moviematch.moviematch.enums.Genero;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @ElementCollection(targetClass = Genero.class)
    @Enumerated(EnumType.STRING)
    private List<Genero> generosFavoritos;

    @ManyToMany
    @JoinTable(
            name = "usuario_filmes_assistidos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmesAssistidos;
}
