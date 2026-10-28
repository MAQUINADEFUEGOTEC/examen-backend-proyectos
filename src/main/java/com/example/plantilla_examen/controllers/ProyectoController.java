package com.example.plantilla_examen.controllers;

import com.example.plantilla_examen.models.Proyecto;
import com.example.plantilla_examen.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok(proyectoService.obtenerProyectosPorUsuario(username));
    }

    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return new ResponseEntity<>(proyectoService.crearProyecto(proyecto, username), HttpStatus.CREATED);
    }
}