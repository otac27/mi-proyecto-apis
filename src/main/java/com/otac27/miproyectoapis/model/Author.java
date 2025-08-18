package com.otac27.miproyectoapis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "authors") // Es una buena práctica especificar el nombre de la tabla
@Getter
@Setter
@NoArgsConstructor // Genera un constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Genera un constructor con todos los argumentos
@EqualsAndHashCode(of = "id")
public class Author implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío") // Validación: el nombre no debe ser nulo ni estar en blanco
    @Column(name = "name", nullable = false) // Mapea a la columna 'name' y no permite nulos a nivel de BD
    private String name;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido") // Validación: debe ser un formato de email válido
    @Column(name = "email", nullable = false, unique = true) // Mapea a la columna 'email', no nulo y único
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Por ahora, todos los autores tienen el rol "ROLE_USER"
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email; // Usaremos el email como nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
