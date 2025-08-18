package com.otac27.miproyectoapis.dto;

import com.otac27.miproyectoapis.model.Topic;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {
    // Constructor para mapear fácilmente desde la entidad Topic
    public DatosRespuestaTopico(Topic topico) {
        this(topico.getId(), topico.getTitle(), topico.getMessage(),
             topico.getCreatedAt(), topico.getStatus().toString(),
             topico.getAuthor().getName(), topico.getCourse().getName());
    }
}