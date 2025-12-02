package com.seguridad.app.repository;

import com.seguridad.app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Método mágico de JPA para buscar por nombre de usuario
    Optional<Usuario> findByUsername(String username);
}