package com.api.forohub.domain.curso.validaciones;

import org.springframework.data.domain.Page;

import java.util.Optional;

public record DatosCursoTopicos(DatosCurso curso, Page<DatosTopico> topicos) {
    public <U> DatosCursoTopicos(DatosCurso curso, Optional<U> topicos) {
    }
}
