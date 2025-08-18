package com.otac27.miproyectoapis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor,
        @NotNull(message = "El ID del curso es obligatorio")
        Long idCurso
) {}