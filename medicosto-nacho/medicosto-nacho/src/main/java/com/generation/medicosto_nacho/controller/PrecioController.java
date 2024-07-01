package com.generation.medicosto_nacho.controller;


import com.generation.medicosto_nacho.models.Precio;
import com.generation.medicosto_nacho.repository.PrecioRepository;
import com.generation.medicosto_nacho.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precios")
public class PrecioController {

    @Autowired
    private PrecioService precioService;

    @GetMapping
    public List<Precio> getAllPrecios() {
        return precioService.getAllPrecios();
    }

    @GetMapping("/{id}")
    public Precio getPrecioById(@PathVariable Long id) {
        return precioService.getPrecioById(id)
                .orElseThrow(() -> new RuntimeException("Precio no encontrado"));
    }

    @PostMapping
    public Precio createPrecio(@RequestBody Precio precio) {
        return precioService.createPrecio(precio);
    }

    @PutMapping("/{id}")
    public Precio updatePrecio(@PathVariable Long id, @RequestBody Precio precio) {
        return precioService.updatePrecio(id, precio);
    }

    @DeleteMapping("/{id}")
    public void deletePrecio(@PathVariable Long id) {
        precioService.deletePrecio(id);
    }
}
