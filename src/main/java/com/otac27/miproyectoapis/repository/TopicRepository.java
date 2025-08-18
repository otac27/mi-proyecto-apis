package com.otac27.miproyectoapis.repository;

import com.otac27.miproyectoapis.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    // Este nuevo método filtrará automáticamente los tópicos inactivos
    Page<Topic> findByActiveTrue(Pageable paginacion);

    // Métodos para validar duplicados
    boolean existsByTitleAndMessage(String title, String message);
    Optional<Topic> findByTitleAndMessage(String title, String message);
}