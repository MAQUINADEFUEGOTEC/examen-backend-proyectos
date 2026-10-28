package com.example.plantilla_examen.repositories;

import com.example.plantilla_examen.models.Proyecto;
import com.example.plantilla_examen.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByUsuario(Usuario usuario);
    long countByUsuario(Usuario usuario);
}