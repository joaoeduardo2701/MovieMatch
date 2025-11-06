package com.moviematch.moviematch.repository;

import com.moviematch.moviematch.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Busca avaliações por filme
    List<Avaliacao> findByFilmeId(Long filmeId);

    // Busca avaliações por usuário
    List<Avaliacao> findByUsuarioId(Long usuarioId);
}
