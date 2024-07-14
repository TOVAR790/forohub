package com.api.forohub.Controller;


import com.api.forohub.domain.curso.topico.Repuesta.validaciones.DatosActualizarTopico;
import com.api.forohub.domain.curso.topico.Repuesta.validaciones.TopicoService;
import com.api.forohub.domain.curso.validaciones.DatosTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody  @Valid DatosActualizarTopico datos) {
        var response = service.actualizarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var response = service.actualizarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var response = service.eliminarTopico(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/resueltos")
    public ResponseEntity<org.springframework.data.domain.Page<DatosTopico>> listarTopicosResueltos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosResueltos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/irresolutos")
    public ResponseEntity<org.springframework.data.domain.Page<DatosTopico>> listarTopicosIrresolutos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosIrresolutos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicoRespuestas(@PageableDefault(size = 10)Pageable paginacion,
                                                  @PathVariable Long id) {
        var response = service.mostrarTopico(id, paginacion);
        return ResponseEntity.ok(response);
    }
}
