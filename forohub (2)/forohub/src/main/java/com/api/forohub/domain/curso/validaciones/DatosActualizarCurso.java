package com.api.forohub.domain.curso.validaciones;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        Categoria categoria
) {

}
