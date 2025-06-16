package com.example.biblioteca.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;


@Entity
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String dni;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos;

	//Constructores
	
	public Usuario() {
		
	}

	public Usuario(Long id, String nombre, String email, String dni) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
	}
	
	//Getters y setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}
	



