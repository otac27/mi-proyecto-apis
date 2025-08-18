package com.otac27.miproyectoapis.model;

import jakarta.persistence.*;
import com.otac27.miproyectoapis.dto.DatosRegistroTopico;
import com.otac27.miproyectoapis.dto.DatosActualizarTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(unique = true, nullable = false) // Un tópico debe tener un título único
    private String title;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Column(nullable = false)
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicStatus status = TopicStatus.OPEN; // Estado por defecto

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean active = true;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY) // LAZY es más eficiente
    @JoinColumn(name = "author_id", nullable = false) // Define la columna de la clave foránea
    private Author author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false) // Define la columna de la clave foránea
    private Course course;

    // Constructor para facilitar la creación desde el DTO
    public Topic(DatosRegistroTopico datosRegistro, Author autor, Course curso) {
        this.title = datosRegistro.titulo();
        this.message = datosRegistro.mensaje();
        this.author = autor;
        this.course = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            this.title = datos.titulo();
        }
        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            this.message = datos.mensaje();
        }
    }

    public void desactivateTopic() {
        this.active = false;
    }

    // Enum para los estados del tópico (con nombres más descriptivos)
    public enum TopicStatus {
        OPEN,       // Abierto, esperando respuesta
        SOLVED,     // Solucionado
        CLOSED      // Cerrado sin solución
    }
}
