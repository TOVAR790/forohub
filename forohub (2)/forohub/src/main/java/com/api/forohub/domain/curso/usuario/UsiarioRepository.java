package com.api.forohub.domain.curso.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsiarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String username);
}
