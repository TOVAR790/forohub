package com.api.forohub.domain.usuario.UsuarioRepository;

import com.api.forohub.domain.topico.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String username);
}
