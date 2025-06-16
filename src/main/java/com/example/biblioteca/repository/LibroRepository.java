package com.example.biblioteca.repository;

import com.example.biblioteca.model.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
	//Buscar libro por titulo
	@Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
	List<Libro> buscarPorTitulo(@Param("titulo") String titulo);
	
	//Buscar libro por autor
		@Query("SELECT l FROM Libro l WHERE LOWER(l.autor) LIKE LOWER(CONCAT('%', :titulo, '%'))")
		List<Libro> buscarPorAutor(@Param("autor") String autor);

}