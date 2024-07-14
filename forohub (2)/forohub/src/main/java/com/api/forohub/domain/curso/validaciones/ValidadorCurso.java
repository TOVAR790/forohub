package com.api.forohub.domain.curso.validaciones;

public interface ValidadorCurso {
    public void valiar(DatosCrearCurso datos);

    void validar(DatosCrearCurso datos) throws ValidacionDeIntegridad;
}
