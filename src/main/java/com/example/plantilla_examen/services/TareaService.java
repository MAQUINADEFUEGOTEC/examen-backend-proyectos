package com.example.plantilla_examen.services;

import com.example.plantilla_examen.models.Proyecto;
import com.example.plantilla_examen.models.Tarea;
import com.example.plantilla_examen.repositories.ProyectoRepository;
import com.example.plantilla_examen.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    public Tarea crearTarea(String titulo, String estado, Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado"));

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        if (estado != null && !estado.isBlank()) {
            tarea.setEstado(estado);
        }
        tarea.setProyecto(proyecto);
        return tareaRepository.save(tarea);
    }

    public Tarea actualizarEstado(Long tareaId, String nuevoEstado) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"));

        tarea.setEstado(nuevoEstado);
        return tareaRepository.save(tarea);
    }
}