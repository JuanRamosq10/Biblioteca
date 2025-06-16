package com.example.biblioteca.service;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Guardar
    public LibroDTO guardar(LibroDTO dto) {
        Libro libro = convertirADominio(dto);
        return convertirADTO(libroRepository.save(libro));
    }

    // Obtener todos
    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    // Obtener por ID
    public Optional<LibroDTO> obtenerPorId(Long id) {
        return libroRepository.findById(id).map(this::convertirADTO);
    }

    // Buscar por título
    public List<LibroDTO> buscarPorTitulo(String titulo) {
        return libroRepository.buscarPorTitulo(titulo).stream()
                .map(this::convertirADTO)
                .toList();
    }
    
    //Buscar por autor
    public List<LibroDTO> buscarPorAutor(String autor) {
        return libroRepository.buscarPorAutor(autor).stream()
                .map(this::convertirADTO)
                .toList();
    }
    

    // Actualizar
    public Optional<LibroDTO> actualizar(Long id, LibroDTO dto) {
        return libroRepository.findById(id).map(libroExistente -> {
            libroExistente.setTitulo(dto.getTitulo());
            libroExistente.setAutor(dto.getAutor());
            libroExistente.setIsbn(dto.getIsbn());
            libroExistente.setCategoria(dto.getCategoria());
            libroExistente.setEjemplaresDisponibles(dto.getEjemplaresDisponibles());
            return convertirADTO(libroRepository.save(libroExistente));
        });
    }

    // Eliminar
    public boolean eliminar(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Conversión entidad -> DTO
    private LibroDTO convertirADTO(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getCategoria(),
                libro.getEjemplaresDisponibles()
        );
    }

    // Conversión DTO -> entidad
    private Libro convertirADominio(LibroDTO dto) {
        return new Libro(
                dto.getTitulo(),
                dto.getAutor(),
                dto.getIsbn(),
                dto.getCategoria(),
                dto.getEjemplaresDisponibles()
        );
    }
}

