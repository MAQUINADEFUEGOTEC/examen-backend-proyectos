package com.example.plantilla_examen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación: Muchos proyectos pertenecen a un usuario (N a 1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación: Un proyecto tiene muchas tareas (1 a N)
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarea> tareas;
}