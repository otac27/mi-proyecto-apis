package com.otac27.miproyectoapis.controller;

import com.otac27.miproyectoapis.dto.DatosActualizarTopico;
import com.otac27.miproyectoapis.dto.DatosListadoTopico;
import com.otac27.miproyectoapis.dto.DatosRegistroTopico;
import com.otac27.miproyectoapis.dto.DatosRespuestaTopico;
import com.otac27.miproyectoapis.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos", description = "Operaciones para crear, leer, actualizar y eliminar tópicos del foro.")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo tópico en la base de datos")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro, UriComponentsBuilder uriBuilder) {
        var datosRespuesta = topicService.registrarTopico(datosRegistro);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    @Operation(summary = "Obtiene un listado paginado de los tópicos activos")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10, sort = "createdAt") Pageable paginacion) {
        // La lógica de negocio ahora está en el Service
        var page = topicService.listarTopicosActivos(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los detalles de un tópico específico por su ID")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        var datosRespuesta = topicService.detallarTopico(id);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un tópico existente")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizar) {
        var datosRespuesta = topicService.actualizarTopico(id, datosActualizar);
        return ResponseEntity.ok(datosRespuesta);
    }

    // ***** MÉTODO DE ELIMINACIÓN (LÓGICA) *****
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un tópico (borrado lógico)")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicService.eliminarTopico(id); // Llamada correcta al servicio
        return ResponseEntity.noContent().build(); // HTTP 204: Sin Contenido (éxito en la eliminación)
    }
}