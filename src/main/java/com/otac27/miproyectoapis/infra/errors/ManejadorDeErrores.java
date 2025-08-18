package com.otac27.miproyectoapis.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DatosErrorGenerico> tratarError404(EntityNotFoundException e) {
        return ResponseEntity.notFound().body(new DatosErrorGenerico(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity<DatosErrorGenerico> errorHandlerValidacionesDeIntegridad(ValidacionDeIntegridad e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DatosErrorGenerico(e.getMessage()));
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    // DTO para errores genéricos con un solo mensaje
    private record DatosErrorGenerico(String mensaje) {}
}