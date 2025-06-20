package com.example.biblioteca.dto;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String dni;

    // Constructores
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String email, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
    }

    // Getters y setters
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
