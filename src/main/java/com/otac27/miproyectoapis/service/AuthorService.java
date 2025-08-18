package com.otac27.miproyectoapis.service;

import com.otac27.miproyectoapis.dto.DatosDetalleAuthor;
import com.otac27.miproyectoapis.dto.DatosRegistroAuthor;
import com.otac27.miproyectoapis.infra.errors.ValidacionDeIntegridad;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public Author registrarAuthor(DatosRegistroAuthor datos) {
        if (authorRepository.findByEmail(datos.email()).isPresent()) {
            throw new ValidacionDeIntegridad("El email ya está en uso.");
        }

        String hashedPassword = passwordEncoder.encode(datos.password());
        Author author = new Author(null, datos.nombre(), datos.email(), hashedPassword);

        return authorRepository.save(author);
    }

    public DatosDetalleAuthor detallarAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor con ID " + id + " no encontrado."));
        return new DatosDetalleAuthor(author);
    }
}