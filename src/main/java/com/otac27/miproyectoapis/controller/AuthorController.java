package com.otac27.miproyectoapis.controller;

import com.otac27.miproyectoapis.dto.DatosDetalleAuthor;
import com.otac27.miproyectoapis.dto.DatosRegistroAuthor;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authors")
@Tag(name = "Autores", description = "Operaciones relacionadas con los autores (usuarios) del foro.")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @Operation(summary = "Registra un nuevo autor en la base de datos", description = "Este endpoint es público.")
    public ResponseEntity<DatosDetalleAuthor> registrarAuthor(@RequestBody @Valid DatosRegistroAuthor datos, UriComponentsBuilder uriBuilder) {
        // La lógica de negocio ahora vive en el servicio
        Author author = authorService.registrarAuthor(datos);
        DatosDetalleAuthor datosDetalleAuthor = new DatosDetalleAuthor(author);

        URI url = uriBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleAuthor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los detalles de un autor específico por su ID", description = "Requiere autenticación.")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosDetalleAuthor> getAuthorById(@PathVariable Long id) {
        var authorDetails = authorService.detallarAuthor(id);
        return ResponseEntity.ok(authorDetails);
    }
}