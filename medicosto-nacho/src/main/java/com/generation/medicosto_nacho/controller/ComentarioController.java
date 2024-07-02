package com.generation.medicosto_nacho.controller;

import com.generation.medicosto_nacho.models.Comentario;
import com.generation.medicosto_nacho.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.getAllComentarios();
    }

    @GetMapping("/{id}")
    public Comentario getComentarioById(@PathVariable Long id) {
        return comentarioService.getComentarioById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    @PostMapping
    public Comentario createComentario(@RequestBody Comentario comentario) {
        return comentarioService.createComentario(comentario);
    }

    @PutMapping("/{id}")
    public Comentario updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        return comentarioService.updateComentario(id, comentario);
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
    }
}
