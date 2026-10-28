package com.example.plantilla_examen.controllers;

import com.example.plantilla_examen.models.Usuario;
import com.example.plantilla_examen.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(authService.registrar(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.login(usuario.getUsername(), usuario.getPassword()));
    }
}