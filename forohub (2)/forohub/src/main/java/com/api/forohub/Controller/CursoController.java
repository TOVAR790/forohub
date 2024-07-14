package com.api.forohub.Controller;


import com.api.forohub.domain.curso.validaciones.CursoService;
import com.api.forohub.domain.curso.validaciones.DatosActualizarCurso;
import com.api.forohub.domain.curso.validaciones.DatosCrearCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearCurso(@RequestBody @Valid DatosCrearCurso datos) {
        var response = service.crearCurso(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos) {
        var response = service.actualizarCurso(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cambiarEstadoCurso(@PathVariable Long id){
        service.cambiarEstado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
    public ResponseEntity.BodyBuilder listarCursosActivos(@PageableDefault(size=10) Pageable paginacion) {
        service.listarCursosActivos(paginacion);
        return ResponseEntity.ok();
    }

    @GetMapping("/inactivos")
    public ResponseEntity<Object> listarCursosInactivos(@PageableDefault(size=10) Pageable paginacion) {
        var response = service.listarCursosInactivos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity.BodyBuilder listarCursos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarCursos(paginacion);
        return ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarCurso(@PageableDefault(size = 10)Pageable paginacion,
                                       @PathVariable @Valid Long id) {
        var response = service.mostrarCurso(id, paginacion);
        return ResponseEntity.ok(response);
    }
}


