package com.moviematch.moviematch.repository;

import com.moviematch.moviematch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
