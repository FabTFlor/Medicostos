package com.generation.medicosto_nacho.service;

import com.generation.medicosto_nacho.models.Comentario;
import com.generation.medicosto_nacho.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario createComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public Comentario updateComentario(Long id, Comentario comentario) {
        return comentarioRepository.findById(id)
                .map(existingComentario -> {
                    existingComentario.setNombre(comentario.getNombre());
                    existingComentario.setApellido(comentario.getApellido());
                    existingComentario.setCorreo(comentario.getCorreo());
                    existingComentario.setContenido(comentario.getContenido());
                    return comentarioRepository.save(existingComentario);
                })
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}

