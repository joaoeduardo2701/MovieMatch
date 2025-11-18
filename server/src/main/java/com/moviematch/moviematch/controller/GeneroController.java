package com.moviematch.moviematch.controller;

import com.moviematch.moviematch.enums.Genero;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class GeneroController {

    @GetMapping
    public Genero[] listarGeneros() {
        return Genero.values();
    }

}