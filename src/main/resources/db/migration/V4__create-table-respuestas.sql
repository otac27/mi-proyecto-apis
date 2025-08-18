-- Creación de la tabla de respuestas
CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    solucion TINYINT(1) DEFAULT 0,
    author_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (topic_id) REFERENCES topics(id)
);