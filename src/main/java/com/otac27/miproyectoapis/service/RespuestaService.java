package com.otac27.miproyectoapis.service;

import com.otac27.miproyectoapis.dto.DatosDetalleRespuesta;
import com.otac27.miproyectoapis.dto.DatosRegistroRespuesta;
import com.otac27.miproyectoapis.model.Author;
import com.otac27.miproyectoapis.model.Respuesta;
import com.otac27.miproyectoapis.model.Topic;
import com.otac27.miproyectoapis.repository.RespuestaRepository;
import com.otac27.miproyectoapis.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public DatosDetalleRespuesta registrarRespuesta(Long topicoId, DatosRegistroRespuesta datos, Author autor) {
        Topic topico = topicRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID " + topicoId + " no encontrado."));

        // Se crea la respuesta y se asignan los valores para usar los defaults de la entidad (fecha, solucion)
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setTopico(topico);
        respuesta.setAutor(autor);
        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);
    }
}