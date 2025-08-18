package com.otac27.miproyectoapis.model; // Paquete corregido

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Column(nullable = false)
    private String category;
}
