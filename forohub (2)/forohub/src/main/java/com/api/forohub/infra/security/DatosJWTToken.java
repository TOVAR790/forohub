package com.api.forohub.infra.security;

import org.springframework.security.core.token.Token;

public record DatosJWTToken(String jwTtoken) {
    public DatosJWTToken(Token jwTtoken) {
    }
}
