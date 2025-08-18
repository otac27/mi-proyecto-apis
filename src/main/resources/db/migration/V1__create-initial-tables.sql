-- Creación de la tabla de autores
CREATE TABLE authors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

-- Creación de la tabla de cursos
CREATE TABLE courses (
     id BIGINT NOT NULL AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL UNIQUE,
     description VARCHAR(255) NOT NULL,
     category VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
 );

-- Creación de la tabla de tópicos
CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    status VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);