package com.dh.clavecompas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dh.clavecompas.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
}
