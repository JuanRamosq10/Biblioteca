package com.example.biblioteca.repository;

import com.example.biblioteca.model.Prestamo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query("SELECT p FROM Prestamo p WHERE p.estado = 'ACTIVO'")
    List<Prestamo> listarPrestamosActivos();
}
