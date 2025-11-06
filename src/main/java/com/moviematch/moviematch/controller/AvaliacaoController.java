package com.moviematch.moviematch.controller;

import com.moviematch.moviematch.model.Avaliacao;
import com.moviematch.moviematch.model.Filme;
import com.moviematch.moviematch.model.Usuario;
import com.moviematch.moviematch.repository.AvaliacaoRepository;
import com.moviematch.moviematch.repository.FilmeRepository;
import com.moviematch.moviematch.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final FilmeRepository filmeRepository;
    private final UsuarioRepository usuarioRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository,
                               FilmeRepository filmeRepository,
                               UsuarioRepository usuarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.filmeRepository = filmeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // DTO para criar avaliação
    public static class AvaliacaoRequest {
        private int estrelas;
        private String comentario;

        public int getEstrelas() { return estrelas; }
        public void setEstrelas(int estrelas) { this.estrelas = estrelas; }
        public String getComentario() { return comentario; }
        public void setComentario(String comentario) { this.comentario = comentario; }
    }

    // 🎬 POST - Criar nova avaliação
    @PostMapping("/usuarios/{usuarioId}/filmes/{filmeId}")
    public ResponseEntity<String> criarAvaliacao(
            @PathVariable Long usuarioId,
            @PathVariable Long filmeId,
            @RequestBody AvaliacaoRequest request) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Filme> filmeOpt = filmeRepository.findById(filmeId);

        if (usuarioOpt.isEmpty() || filmeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(usuarioOpt.get());
        avaliacao.setFilme(filmeOpt.get());
        avaliacao.setEstrelas(request.getEstrelas()); // ← aqui
        avaliacao.setComentario(request.getComentario());

        avaliacaoRepository.save(avaliacao);

        return ResponseEntity.ok("Avaliação cadastrada com sucesso!");
    }

    // 🎬 GET - Listar avaliações de um filme
    @GetMapping("/filmes/{filmeId}")
    public ResponseEntity<List<Avaliacao>> listarPorFilme(@PathVariable Long filmeId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByFilmeId(filmeId);
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }

    // 🎬 GET - Listar avaliações de um usuário
    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Avaliacao>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioId(usuarioId);
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }

    // 🎬 GET - Listar todas as avaliações
    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodas() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }
}
