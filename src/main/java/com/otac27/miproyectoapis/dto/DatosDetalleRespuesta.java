package com.otac27.miproyectoapis.dto;

import com.otac27.miproyectoapis.model.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getCreatedAt(), respuesta.getAutor().getName(), respuesta.getSolucion());
    }
}