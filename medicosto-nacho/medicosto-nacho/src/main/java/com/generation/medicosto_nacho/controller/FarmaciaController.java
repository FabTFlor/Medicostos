package com.generation.medicosto_nacho.controller;


import com.generation.medicosto_nacho.models.Farmacia;
import com.generation.medicosto_nacho.repository.FarmaciaRepository;
import com.generation.medicosto_nacho.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacias")

public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping
    public List<Farmacia> getAllFarmacias() {
        return farmaciaService.getAllFarmacias();
    }

    @GetMapping("/{id}")
    public Farmacia getFarmaciaById(@PathVariable Long id) {
        return farmaciaService.getFarmaciaById(id)
                .orElseThrow(() -> new RuntimeException("Farmacia no encontrada"));
    }

    @PostMapping
    public Farmacia createFarmacia(@RequestBody Farmacia farmacia) {
        return farmaciaService.createFarmacia(farmacia);
    }

    @PutMapping("/{id}")
    public Farmacia updateFarmacia(@PathVariable Long id, @RequestBody Farmacia farmacia) {
        return farmaciaService.updateFarmacia(id, farmacia);
    }

    @DeleteMapping("/{id}")
    public void deleteFarmacia(@PathVariable Long id) {
        farmaciaService.deleteFarmacia(id);
    }
}