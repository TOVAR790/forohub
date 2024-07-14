package com.api.forohub.domain.curso.validaciones;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;



@Component
public class CategoriaValida implements ValidadorCurso{

    @SneakyThrows
    @Override
    public void validar(DatosCrearCurso datos) {
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Asignarle una categoria al curso");
        }


    }

    @Override
    public void valiar(DatosCrearCurso datos) {

    }
}
