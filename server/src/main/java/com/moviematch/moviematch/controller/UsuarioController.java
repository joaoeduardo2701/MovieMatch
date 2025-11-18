package com.moviematch.moviematch.controller;

import com.moviematch.moviematch.dto.UsuarioRequest;
import com.moviematch.moviematch.enums.Genero;
import com.moviematch.moviematch.model.Usuario;
import com.moviematch.moviematch.model.Filme;
import com.moviematch.moviematch.repository.UsuarioRepository;
import com.moviematch.moviematch.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, FilmeRepository filmeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
    }

    // 🎬 POST - Criar novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        // Buscar filmes assistidos pelo ID
        List<Filme> filmes = filmeRepository.findAllById(usuarioRequest.getFilmesAssistidosIds());

        Usuario usuario = Usuario.builder()
                .nome(usuarioRequest.getNome())
                .email(usuarioRequest.getEmail())
                .generosFavoritos(usuarioRequest.getGenerosFavoritos())
                .filmesAssistidos(filmes)
                .build();

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    // 🎬 GET - Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    // 🎬 GET - Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Usuário não encontrado"));
    }

    // 🎬 DELETE - Deletar usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

    // 🎬 GET - Filmes assistidos de um usuário
    @GetMapping("/{id}/filmesAssistidos")
    public ResponseEntity<List<Filme>> listarFilmesAssistidos(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario.getFilmesAssistidos()))
                .orElse(ResponseEntity.status(404).build());
    }

    // 🎬 GET - Gêneros favoritos de um usuário específico
    @GetMapping("/{id}/generos-favoritos")
    public ResponseEntity<List<Genero>> listarGenerosFavoritos(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario.getGenerosFavoritos()))
                .orElse(ResponseEntity.status(404).build());
    }
}
