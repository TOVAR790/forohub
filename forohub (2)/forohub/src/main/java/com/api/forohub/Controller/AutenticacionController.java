package com.api.forohub.Controller;


import com.api.forohub.domain.topico.usuario.Usuario;
import com.api.forohub.infra.security.DatosJWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    private DatosAutenticacionUsuario datos;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        this.datos = datos;
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasenia());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.verifyToken(String.valueOf((Usuario) usuarioAutenticado.getPrincipal()));
        ResponseEntity<DatosJWTToken> ok = ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        return ok;
    }
}



