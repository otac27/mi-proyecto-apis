package com.otac27.miproyectoapis.dto;

import com.otac27.miproyectoapis.model.Topic;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String status,
        LocalDateTime fechaCreacion,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topic topico) {
        this(topico.getId(),
             topico.getTitle(),
             topico.getStatus().toString(),
             topico.getCreatedAt(),
             topico.getAuthor().getName(),
             topico.getCourse().getName());
    }
}