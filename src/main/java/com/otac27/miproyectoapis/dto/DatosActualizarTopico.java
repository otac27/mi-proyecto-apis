package com.otac27.miproyectoapis.dto;

// Los campos son opcionales para permitir actualizaciones parciales (solo título, solo mensaje, o ambos).
// Por eso no usamos validaciones como @NotBlank aquí.
public record DatosActualizarTopico(
        String titulo,
        String mensaje
) {
}