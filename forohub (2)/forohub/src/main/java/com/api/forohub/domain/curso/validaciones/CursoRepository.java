package com.api.forohub.domain.curso.validaciones;

public interface CursoRepository {
    boolean existsByNombre(String nombre);

    Object getReferenceById(Object o);
}
