package com.otac27.miproyectoapis.controller;

import com.otac27.miproyectoapis.dto.DatosDetalleRespuesta;
import com.otac27.miproyectoapis.dto.DatosRegistroRespuesta;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.service.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos/{topicoId}/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas", description = "Operaciones relacionadas con las respuestas a un tópico.")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping
    @Operation(
            summary = "Registra una nueva respuesta para un tópico",
            description = "Crea una nueva respuesta asociada a un tópico existente. Requiere autenticación."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta creada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "403", description = "No autenticado para publicar la respuesta")
    })
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(
            @PathVariable Long topicoId,
            @RequestBody @Valid DatosRegistroRespuesta datos,
            @AuthenticationPrincipal Author autor, // Obtenemos el autor autenticado
            UriComponentsBuilder uriBuilder) {

        var datosRespuesta = respuestaService.registrarRespuesta(topicoId, datos, autor);
        URI url = uriBuilder.path("/topicos/{topicoId}/respuestas/{respuestaId}").buildAndExpand(topicoId, datosRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }
}