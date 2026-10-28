package com.example.plantilla_examen.controllers;

import com.example.plantilla_examen.models.Tarea;
import com.example.plantilla_examen.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Map<String, Object> body) {
        String titulo = body.get("titulo").toString();
        String estado = body.containsKey("estado") ? body.get("estado").toString() : "PENDIENTE";
        Long proyectoId = Long.valueOf(body.get("proyecto_id").toString());

        return new ResponseEntity<>(tareaService.crearTarea(titulo, estado, proyectoId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(tareaService.actualizarEstado(id, nuevoEstado));
    }
}