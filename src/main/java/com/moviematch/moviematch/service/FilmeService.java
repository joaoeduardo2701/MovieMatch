package com.moviematch.moviematch.service;

import com.moviematch.moviematch.model.Filme;
import com.moviematch.moviematch.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // Criar (cadastrar) filme
    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    // Listar todos os filmes
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    // Buscar por ID
    public Optional<Filme> buscarPorId(Long id) {
        return filmeRepository.findById(id);
    }

    // Deletar
    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }
}
