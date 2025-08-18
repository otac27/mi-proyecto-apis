package com.otac27.miproyectoapis.controller;

import com.otac27.miproyectoapis.dto.DatosRegistroAuthor;
import com.otac27.miproyectoapis.infra.errors.ValidacionDeIntegridad;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.repository.AuthorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> registrarAuthor(@RequestBody @Valid DatosRegistroAuthor datos, UriComponentsBuilder uriBuilder) {
        if (authorRepository.findByEmail(datos.email()).isPresent()) {
            throw new ValidacionDeIntegridad("El email ya está en uso.");
        }

        Author author = new Author(null, datos.nombre(), datos.email(), passwordEncoder.encode(datos.password()));
        authorRepository.save(author);

        URI url = uriBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(url).body("Usuario registrado exitosamente. ID: " + author.getId());
    }
}