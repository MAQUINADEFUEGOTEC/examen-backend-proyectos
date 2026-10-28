package com.example.plantilla_examen.services;

import com.example.plantilla_examen.models.Proyecto;
import com.example.plantilla_examen.models.Usuario;
import com.example.plantilla_examen.repositories.ProyectoRepository;
import com.example.plantilla_examen.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Proyecto> obtenerProyectosPorUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return proyectoRepository.findByUsuario(usuario);
    }

    public Proyecto crearProyecto(Proyecto proyecto, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // REGLA DE NEGOCIO: Máximo 3 proyectos por usuario
        long cantidadProyectos = proyectoRepository.countByUsuario(usuario);
        if (cantidadProyectos >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Has alcanzado el límite máximo de 3 proyectos activos.");
        }

        proyecto.setUsuario(usuario);
        return proyectoRepository.save(proyecto);
    }
}