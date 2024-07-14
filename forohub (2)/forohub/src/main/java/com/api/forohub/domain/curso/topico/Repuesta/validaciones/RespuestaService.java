package com.api.forohub.domain.curso.topico.Repuesta.validaciones;

import com.api.forohub.domain.curso.validaciones.DatosTopico;
import com.api.forohub.domain.curso.validaciones.ValidacionDeIntegridad;
import com.api.forohub.domain.usuario.UsuarioRepository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<ValidadorRespuesta> validarRespuesta;

    public DatosRespuesta crearRespuesta(DatosCrearRespuesta datos) {

        validarRespuesta.forEach(v -> v.validar(datos));
        var mensaje = datos.mensaje();
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var respuesta = new Respuesta(mensaje, autor, topico);
        repository.save(respuesta);
        return new DatosRespuesta(respuesta);
    }

    public DatosRespuesta editarRespuesta(DatosEditarRespuesta datos) throws ValidacionDeIntegridad {
        if (datos.mensaje() == null) {
            throw new ValidationException("respuesta no encontrada");
        }
        if (!repository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("id de respuesta no válido");
        }
        var respuesta = repository.getReferenceById(datos.idAutor());
        respuesta.actualizarMensaje(datos.mensaje());
        return new DatosRespuesta(respuesta);
    }

    public EstadoTopicoRespuesta marcarDesmarcarComoSolucion(Long id) throws ValidacionDeIntegridad {
        idValido(id);
        var respuesta = repository.getReferenceById(id);
        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());
        respuesta.setSolucion();
        var solucionado = repository.existsByTopicoAndSolucion((Topico) topico, true);
        System.out.println(solucionado);
        if ((solucionado && ((Topico) topico).getStatus() == false) || (!solucionado && ((Topico) topico).getStatus() == true)) {
            ((Topico) topico).setStatus();
        }

        var resultado = new EstadoTopicoRespuesta(new DatosTopico(topico), new DatosRespuesta(respuesta));
        return resultado;
    }

    public void eliminarRespuesta(Long id) {
        try {
            idValido(id);
        } catch (ValidacionDeIntegridad e) {
            throw new RuntimeException(e);
        }
        var idTopicoRespuesta = repository.getReferenceById(id).getTopico().getId();
        var topico = topicoRepository.getReferenceById(idTopicoRespuesta);

        repository.deleteById(id);

        var topicoSolucionado = repository.existsByTopicoAndSolucion((Topico) topico, true);
        var estadoTopico = ((Topico) topico).getStatus();
        if (estadoTopico && !topicoSolucionado) {
            ((Topico) topico).setStatus();
        }
    }

    private void idValido(Long id) throws ValidacionDeIntegridad {
        if(id == null) {
            throw new ValidationException("Debe proporcionar el id de la respuesta");
        }

        if(!repository.existsById(id)) {
            throw new ValidacionDeIntegridad("No existe respuesta con id: " + id);
        }
    }

}
