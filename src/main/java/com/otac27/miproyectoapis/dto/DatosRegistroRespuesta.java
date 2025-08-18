package com.otac27.miproyectoapis.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje
) {}