package com.example.biblioteca.service;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.repository.PrestamoRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           LibroRepository libroRepository,
                           UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Convierte entidad Prestamo a DTO, convirtiendo enum a String
    private PrestamoDTO convertirADTO(Prestamo prestamo) {
        return new PrestamoDTO(
                prestamo.getId(),
                prestamo.getLibro().getId(),
                prestamo.getUsuario().getId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                prestamo.getEstado().name()  // enum -> String
        );
    }

    // Convierte String a enum, con control de valores inválidos
    private Prestamo.EstadoPrestamo convertirAEstado(String estadoStr) {
        try {
            return Prestamo.EstadoPrestamo.valueOf(estadoStr.toUpperCase());
        } catch (Exception e) {
            return Prestamo.EstadoPrestamo.ACTIVO; // valor por defecto
        }
    }

    // Realizar préstamo verificando disponibilidad
    public Optional<PrestamoDTO> realizarPrestamo(Long libroId, Long usuarioId) {
        Optional<Libro> libroOpt = libroRepository.findById(libroId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (libroOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Libro libro = libroOpt.get();
        if (libro.getEjemplaresDisponibles() == null || libro.getEjemplaresDisponibles() <= 0) {
            return Optional.empty();  // No hay ejemplares disponibles
        }

        // Reducir ejemplares disponibles
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuarioOpt.get());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setEstado(Prestamo.EstadoPrestamo.ACTIVO);
        prestamo.setFechaDevolucion(null);

        Prestamo guardado = prestamoRepository.save(prestamo);
        return Optional.of(convertirADTO(guardado));
    }

    // Devolver préstamo (cambia estado y fecha de devolución)
    public Optional<PrestamoDTO> devolverPrestamo(Long prestamoId) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(prestamoId);

        if (prestamoOpt.isEmpty()) {
            return Optional.empty();
        }

        Prestamo prestamo = prestamoOpt.get();

        if (prestamo.getEstado() == Prestamo.EstadoPrestamo.DEVUELTO) {
            return Optional.empty();  // Ya devuelto
        }

        prestamo.setEstado(Prestamo.EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamoRepository.save(prestamo);

        // Incrementar ejemplares disponibles del libro
        Libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepository.save(libro);

        return Optional.of(convertirADTO(prestamo));
    }

    // Listar todos los préstamos
    public List<PrestamoDTO> listarTodos() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    // Devolver prestamo por ID
    
    public Optional<PrestamoDTO> getPrestamoById(Long prestamoId) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(prestamoId);
        return prestamoOpt.map(this::convertirADTO);
    }
    
    //Obtener prestamos por usuario

    public List<PrestamoDTO> obtenerPrestamosPorUsuario(Long idUsuario) {
        return prestamoRepository.findAll()
                .stream()
                .filter(prestamo -> prestamo.getUsuario().getId().equals(idUsuario))
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    //Listar prestamos activos
    public List<PrestamoDTO> obtenerPrestamosActivos() {
        return prestamoRepository.listarPrestamosActivos()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


}
