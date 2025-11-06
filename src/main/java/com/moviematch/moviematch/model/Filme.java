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
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String descricao;

    private double notaMedia;

    @ElementCollection
    private List<String> tags;

    private String imagemUrl;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;

    // ✅ Construtor customizado para uso no DataLoader
    public Filme(Long id, String titulo, Genero genero, String descricao, double notaMedia, List<String> tags, String imagemUrl) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.descricao = descricao;
        this.notaMedia = notaMedia;
        this.tags = tags;
        this.imagemUrl = imagemUrl;
    }
}
