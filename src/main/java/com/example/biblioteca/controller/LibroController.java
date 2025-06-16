package com.example.biblioteca.controller;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public LibroDTO crearLibro(@RequestBody LibroDTO libroDTO) {
        return libroService.guardar(libroDTO);
    }

    @GetMapping
    public List<LibroDTO> listarLibros() {
        return libroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscarTitulo")
    public List<LibroDTO> buscarPorTitulo(@RequestParam String titulo) {
        return libroService.buscarPorTitulo(titulo);
    }
    
    @GetMapping("/buscarAutor")
    public List<LibroDTO> buscarPorAutor(@RequestParam String autor) {
        return libroService.buscarPorAutor(autor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizar(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        return libroService.actualizar(id, libroDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return libroService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

