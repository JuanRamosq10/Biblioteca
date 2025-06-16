package com.example.biblioteca.service;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.biblioteca.dto.UsuarioDTO;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Convertir de entidad a DTO
    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getDni()
        );
    }

    // Convertir de DTO a entidad
    private Usuario convertirADominio(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setDni(dto.getDni());
        return usuario;
    }

    // Crear usuario
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertirADominio(usuarioDTO);
        Usuario guardado = usuarioRepository.save(usuario);
        return convertirAUsuarioDTO(guardado);
    }

    // Obtener todos los usuarios
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAUsuarioDTO)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    public Optional<UsuarioDTO> obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirAUsuarioDTO);
    }

    // Actualizar usuario
    public Optional<UsuarioDTO> actualizar(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setDni(usuarioDTO.getDni());
            Usuario actualizado = usuarioRepository.save(usuario);
            return convertirAUsuarioDTO(actualizado);
        });
    }

    // Eliminar usuario
    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
