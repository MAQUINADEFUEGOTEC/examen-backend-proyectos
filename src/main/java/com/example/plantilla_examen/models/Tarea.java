package com.example.plantilla_examen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 20)
    private String estado = "PENDIENTE";

    // Relación: Muchas tareas pertenecen a un proyecto (N a 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    @JsonIgnore // Evita que la tarea vuelva a llamar al proyecto infinitamente
    private Proyecto proyecto;
}