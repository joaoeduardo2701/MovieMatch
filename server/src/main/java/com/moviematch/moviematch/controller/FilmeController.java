package com.moviematch.moviematch.controller;

import com.moviematch.moviematch.dto.AvaliacaoFilmeDTO;
import com.moviematch.moviematch.enums.Genero;
import com.moviematch.moviematch.model.Avaliacao;
import com.moviematch.moviematch.model.Filme;
import com.moviematch.moviematch.repository.AvaliacaoRepository;
import com.moviematch.moviematch.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository; // injetando o repositório

    // POST - cadastrar filme
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme) {
        Filme salvo = filmeService.salvar(filme);
        return ResponseEntity.ok(salvo);
    }

    // GET - listar todos
    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> filmes = filmeService.listarTodos();
        return ResponseEntity.ok(filmes);
    }

    // GET - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        return filmeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - deletar filme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // GET - listar gêneros
    @GetMapping("/generos")
    public ResponseEntity<List<String>> listarGeneros() {
        List<String> generos = Arrays.stream(Genero.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(generos);
    }

    // GET - listar avaliações de um filme específico
    @GetMapping("/{filmeId}/avaliacoes")
    public ResponseEntity<List<AvaliacaoFilmeDTO>> listarAvaliacoesDoFilme(@PathVariable Long filmeId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByFilmeId(filmeId);

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AvaliacaoFilmeDTO> resultado = avaliacoes.stream()
                .map(a -> new AvaliacaoFilmeDTO(
                        a.getUsuario().getNome(),
                        a.getEstrelas(),
                        a.getComentario()
                ))
                .toList();

        return ResponseEntity.ok(resultado);
    }
}
