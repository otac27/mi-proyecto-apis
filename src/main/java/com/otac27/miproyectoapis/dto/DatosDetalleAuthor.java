package com.otac27.miproyectoapis.dto;

import com.otac27.miproyectoapis.model.Author;

public record DatosDetalleAuthor(
        Long id,
        String nombre,
        String email
) {
    public DatosDetalleAuthor(Author author) {
        this(author.getId(), author.getName(), author.getEmail());
    }
}