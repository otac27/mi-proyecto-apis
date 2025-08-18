package com.otac27.miproyectoapis.controller;

import com.otac27.miproyectoapis.dto.DatosRegistroAuthor;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> registrarAuthor(@RequestBody @Valid DatosRegistroAuthor datos, UriComponentsBuilder uriBuilder) {
        // La lógica de negocio ahora vive en el servicio
        Author author = authorService.registrarAuthor(datos);

        URI url = uriBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(url).build(); // Devuelve 201 Created con la URL y cuerpo vacío
    }
}