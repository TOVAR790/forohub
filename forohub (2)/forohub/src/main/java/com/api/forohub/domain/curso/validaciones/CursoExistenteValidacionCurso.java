package com.api.forohub.domain.curso.validaciones;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoExistenteValidacionCurso implements ValidadorCurso{
    @Override
    public void valiar(DatosCrearCurso datos) {

    }

    @Autowired
    CursoRepository repository;

    @Override
    public void validar(DatosCrearCurso datos) throws ValidacionDeIntegridad {
        if (repository.existsByNombre(datos.nombre())) {
            throw new ValidationException("Ya existe un curso con ese nombre");
        }
        if (datos.nombre() == null) {
            throw new ValidacionDeIntegridad("El nombre del curso no puede quedar vacio");
        }
    }
}


