package com.otac27.miproyectoapis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank @Email String email,
        @NotBlank String password) {}