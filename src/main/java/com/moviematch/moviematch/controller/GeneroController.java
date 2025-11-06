package com.moviematch.moviematch.controller;

import com.moviematch.moviematch.enums.Genero;
import com.moviematch.moviematch.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class GeneroController {

    @GetMapping
    public Genero[] listarGeneros() {
        return Genero.values();
    }

}