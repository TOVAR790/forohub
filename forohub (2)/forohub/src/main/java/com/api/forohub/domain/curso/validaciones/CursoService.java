package com.api.forohub.domain.curso.validaciones;

import com.api.forohub.domain.curso.topico.Repuesta.validaciones.TopicoRepository;
import jakarta.xml.bind.ValidationException;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRespository cursoRespository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorCurso> validadorCursos;
    private TopicoRepository cursoRepository;

    public CursoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public DatosCurso crearCurso(DatosCrearCurso datos) {
        validadorCursos.forEach(v -> {
            try {
                v.validar(datos);
            } catch (ValidacionDeIntegridad e) {
                throw new RuntimeException(e);
            }
        });
        var curso = new Curso(datos.nombre(), datos.categoria());
        cursoRespository.save(curso);
        return new DatosCurso(curso);
    }

    public DatosCurso actualizarCurso(DatosActualizarCurso datos) {
        cursoExistente(datos.id());
        TopicoRepository cursoRepository = null;
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DatosCurso((Curso) curso);
    }

    public DatosCurso cambiarEstado(Long id) {
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        curso.getClass();
        return new DatosCurso((Curso) curso);
    }
    private void cursoExistente(Long id) {
        if (id == null){
            try {
                throw new ValidationException("Proporcionar el id del curso");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        if (!cursoRespository.existsById(id)){
            throw new validacionDeIntegridad("Proporcionar el id de curso valido");
        }
    }
    public Page<DatosCurso> listarCursosActivos(Pageable paginacion){
        return (Page<DatosCurso>) cursoRespository.findByActivoTrue(paginacion).map(DatosCurso::new);
    }
    public Page<DatosCurso> listarCursosActivos(Pageable paginacion){
        return cursoRespository.findByActivoFalse(paginacion).map(DatosCurso::new);
    }
    public Page<DatosCurso> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosCurso::new);
    }

    public Page<DatosCurso> listarCursos(Pageable paginacion){
        return cursoRespository.findAll(paginacion).map(DatosCurso::new);
    }
    public DatosCursoTopicos mostrarCurso(Long id, Pageable paginacion){
        cursoExistente(id);
        var curso = cursoRespository.getReferenceById(id);
        var topicos = topicoRepository.findAllByCurso(curso, paginacion).map(DatosTopico::new);
        return new DatosCursoTopicos(new DatosCurso(curso), topicos);
    }

    public void listarCursosActivos(org.springframework.data.domain.Pageable paginacion) {
        return null;
    }

    public Object listarCursosInactivos(org.springframework.data.domain.Pageable paginacion) {
        return null;
    }

    public Page<DatosCurso> listarCursos(org.springframework.data.domain.Pageable paginacion) {
        return null;
    }

    public DatosCursoTopicos mostrarCurso(Long id, org.springframework.data.domain.Pageable paginacion) {
        return null;

    }

    private record validacionDeIntegridad(String s) {
    }
}
