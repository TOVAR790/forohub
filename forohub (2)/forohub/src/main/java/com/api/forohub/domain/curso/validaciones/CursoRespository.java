package com.api.forohub.domain.curso.validaciones;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRespository  extends JpaRepository<Curso, Long> {

    boolean existsByNombre(String nombre);
    Page<Curso> findByActivoTrue(Pageable paginacion);
    Page<Curso> findByActivoFalse(Pageable paginacion);

}
