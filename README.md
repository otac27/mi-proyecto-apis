# ForoHub API

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.8-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![JWT](https://img.shields.io/badge/JWT-Auth0-orange.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)

API RESTful para la gestión de un foro de discusión, desarrollada como parte del programa ONE (Oracle Next Education) en colaboración con Alura Latam. La API permite a los usuarios registrarse, autenticarse y gestionar tópicos de discusión.

## ✨ Características

- **Autenticación y Autorización:** Sistema de seguridad basado en Tokens JWT (JSON Web Tokens) para proteger los endpoints.
- **Gestión de Usuarios:** Endpoint público para el registro de nuevos usuarios.
- **CRUD de Tópicos:**
  - Creación de nuevos tópicos.
  - Listado paginado y ordenado de todos los tópicos activos.
  - Detalle de un tópico específico.
  - Actualización de un tópico existente.
  - **Borrado Lógico:** Los tópicos no se eliminan físicamente, sino que se marcan como inactivos.
- **Validaciones de Negocio:** Se manejan casos como la duplicidad de tópicos o el registro de usuarios con emails ya existentes.
- **Gestión de Base de Datos:** Migraciones automáticas y versionadas con Flyway.
- **Documentación de API:** Documentación interactiva y automatizada con Swagger (OpenAPI).

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **MySQL:** Base de datos relacional.
- **Flyway:** Para la gestión de migraciones de la base de datos.
- **Lombok:** Para reducir el código boilerplate en las entidades y DTOs.
- **JWT (Java JWT):** Para la generación y validación de tokens de autenticación.
- **Maven:** Como gestor de dependencias y construcción del proyecto.
- **Swagger (SpringDoc):** Para la documentación de la API.

## 📋 Prerrequisitos

- JDK 17 o superior.
- Maven 3.8 o superior.
- Una instancia de MySQL en ejecución.

## ⚙️ Configuración

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-nombre-de-usuario/forohub-api.git
    cd forohub-api
    ```

2.  **Crear la base de datos:**
    Asegúrate de tener una base de datos en MySQL llamada `forohub`.
    ```sql
    CREATE DATABASE forohub;
    ```

3.  **Configurar las credenciales:**
    Abre el archivo `src/main/resources/application.properties` y modifica las siguientes líneas con tus credenciales de MySQL:
    ```properties
    spring.datasource.username=tu_usuario_mysql
    spring.datasource.password=tu_contraseña_mysql
    ```
    > **💡 Tip Profesional:** En un entorno de producción, es una mejor práctica configurar estas credenciales y el secreto de JWT (`api.security.secret`) como variables de entorno para no exponer información sensible en el código fuente. Spring Boot las detectará automáticamente.

## 📸 Capturas de Pantalla
*Aquí puedes agregar capturas de pantalla de tu API funcionando, por ejemplo, una petición en Postman o la interfaz de Swagger.*

## ▶️ Ejecución

Puedes ejecutar la aplicación usando el plugin de Maven para Spring Boot:

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8085`.

## Endpoints de la API

### Autenticación

- **`POST /login`**: Autentica a un usuario y devuelve un token JWT.
  - **Body:** `{ "email": "...", "password": "..." }`

### Autores (Usuarios)

- **`POST /authors`**: Registra un nuevo autor (usuario). Es un endpoint público.
  - **Body:** `{ "nombre": "...", "email": "...", "password": "..." }`
- **`GET /authors/{id}`**: Obtiene los detalles de un autor específico. Requiere autenticación.


### Tópicos (Requieren Autenticación)

Para acceder a estos endpoints, debes incluir el token JWT en la cabecera de la petición:
`Authorization: Bearer <TU_TOKEN_JWT>`

- **`POST /topicos`**: Crea un nuevo tópico.
- **`GET /topicos`**: Lista todos los tópicos activos de forma paginada.
- **`GET /topicos/{id}`**: Obtiene los detalles de un tópico específico.
- **`PUT /topicos/{id}`**: Actualiza un tópico existente.
- **`DELETE /topicos/{id}`**: Desactiva un tópico (borrado lógico).

## 📚 Documentación Interactiva (Swagger)

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API a través de Swagger UI en la siguiente URL:

http://localhost:8085/swagger-ui.html

---
*Proyecto desarrollado por [Reemplaza con tu Nombre Completo].*