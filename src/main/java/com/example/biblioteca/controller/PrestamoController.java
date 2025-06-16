package com.example.biblioteca.controller;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // Realizar un préstamo verificando disponibilidad
    @PostMapping
    public ResponseEntity<PrestamoDTO> realizarPrestamo(@RequestParam Long libroId, @RequestParam Long usuarioId) {
        return prestamoService.realizarPrestamo(libroId, usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
    
    // Listar todos los préstamos
    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> listarTodos() {
        List<PrestamoDTO> prestamos = prestamoService.listarTodos();
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }
    
    // Devolver prestamo por ID
    
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> obtenerPrestamoPorId(@PathVariable Long id) {
        return prestamoService.getPrestamoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    // Devolver un libro prestado
    @PostMapping("/{id}/devolver")
    public ResponseEntity<PrestamoDTO> devolverPrestamoPorId(@PathVariable Long id) {
        return prestamoService.devolverPrestamo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
    
    //Obtener prestamos por usuario
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamosPorUsuario(@PathVariable Long idUsuario) {
        List<PrestamoDTO> prestamos = prestamoService.obtenerPrestamosPorUsuario(idUsuario);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }
    
    //Obtener prestamos activos
    @GetMapping("/activos")
    public ResponseEntity<List<PrestamoDTO>> listarPrestamosActivos() {
        List<PrestamoDTO> prestamos = prestamoService.obtenerPrestamosActivos();
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }

}
