package com.otac27.miproyectoapis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroAuthor(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El email es obligatorio") @Email(message = "El formato del email no es válido") String email,
        @NotBlank(message = "La contraseña es obligatoria") String password
) {
}