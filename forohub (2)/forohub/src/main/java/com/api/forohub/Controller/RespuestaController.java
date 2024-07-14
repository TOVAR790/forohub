package com.api.forohub.Controller;


import com.api.forohub.domain.curso.topico.Repuesta.validaciones.DatosCrearRespuesta;
import com.api.forohub.domain.curso.topico.Repuesta.validaciones.DatosEditarRespuesta;
import com.api.forohub.domain.curso.topico.Repuesta.validaciones.EstadoTopicoRespuesta;
import com.api.forohub.domain.curso.topico.Repuesta.validaciones.RespuestaService;
import com.api.forohub.domain.curso.validaciones.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")

public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosCrearRespuesta datos){
        var response = service.crearRespuesta(datos);
        return ResponseEntity.ok(response);

    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosEditarRespuesta datos) throws ValidacionDeIntegridad {
        var response = service.editarRespuesta(datos);
        return  ResponseEntity.ok(response);
    }
    @PutMapping
    @Transactional
    public  ResponseEntity cambiarEstadoRespuesta(@PathVariable long id) throws ValidacionDeIntegridad {
        EstadoTopicoRespuesta response = service.marcarDesmarcarComoSolucion(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        service.eliminarRespuesta(id);
        return ResponseEntity.ok().build();
    }
}
