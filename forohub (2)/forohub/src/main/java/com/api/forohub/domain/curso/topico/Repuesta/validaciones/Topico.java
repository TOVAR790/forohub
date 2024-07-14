package com.api.forohub.domain.curso.topico.Repuesta.validaciones;

import com.api.forohub.domain.curso.validaciones.Curso;
import com.api.forohub.domain.topico.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_topico_id")
    private Usuario autorTopico;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = false;
        this.autorTopico = autor;
        this.curso = curso;
    }

    public void actualirTopico(DatosActualizarTopico datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
    }

    public void setStatus() {
        this.status = !status;
    }


    public void actualizarTopico(DatosActualizarTopico datos) {

    }
}
