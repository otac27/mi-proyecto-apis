package com.otac27.miproyectoapis.service;

import com.otac27.miproyectoapis.dto.DatosActualizarTopico;
import com.otac27.miproyectoapis.dto.DatosRegistroTopico;
import com.otac27.miproyectoapis.dto.DatosRespuestaTopico;
import com.otac27.miproyectoapis.infra.errors.ValidacionDeIntegridad;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.model.Course;
import com.otac27.miproyectoapis.model.Topic;
import com.otac27.miproyectoapis.repository.AuthorRepository;
import com.otac27.miproyectoapis.repository.CourseRepository;
import com.otac27.miproyectoapis.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final AuthorRepository authorRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistro) {
        if (topicRepository.existsByTitleAndMessage(datosRegistro.titulo(), datosRegistro.mensaje())) {
            throw new ValidacionDeIntegridad("No se pueden crear tópicos duplicados con el mismo título y mensaje.");
        }

        Author autor = authorRepository.findById(datosRegistro.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Autor con ID " + datosRegistro.idAutor() + " no encontrado."));
        Course curso = courseRepository.findById(datosRegistro.idCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso con ID " + datosRegistro.idCurso() + " no encontrado."));

        Topic topico = new Topic(datosRegistro, autor, curso);
        topicRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    public Page<Topic> listarTopicosActivos(Pageable paginacion) {
        // Llama al método del repositorio que filtra por tópicos activos
        return topicRepository.findByActiveTrue(paginacion);
    }

    public DatosRespuestaTopico detallarTopico(Long id) {
        Topic topico = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID " + id + " no encontrado."));
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datos) {
        // 1. Validar que el tópico exista
        Topic topico = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID " + id + " no encontrado."));

        // 2. Validar que no se duplique con otro tópico existente
        if (datos.titulo() != null || datos.mensaje() != null) {
            String nuevoTitulo = datos.titulo() != null ? datos.titulo() : topico.getTitle();
            String nuevoMensaje = datos.mensaje() != null ? datos.mensaje() : topico.getMessage();
            topicRepository.findByTitleAndMessage(nuevoTitulo, nuevoMensaje)
                    .ifPresent(t -> {
                        if (!t.getId().equals(id)) {
                            throw new ValidacionDeIntegridad("Ya existe otro tópico con el mismo título y mensaje.");
                        }
                    });
        }

        // 3. Actualizar los datos
        topico.actualizarDatos(datos);
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        Topic topico = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID " + id + " no encontrado."));
        topico.desactivateTopic();
    }
}