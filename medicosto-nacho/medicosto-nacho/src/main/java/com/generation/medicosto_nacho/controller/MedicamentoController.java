package com.generation.medicosto_nacho.controller;


import com.generation.medicosto_nacho.models.Medicamento;
import com.generation.medicosto_nacho.repository.MedicamentoRepository;
import com.generation.medicosto_nacho.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoService.getAllMedicamentos();
    }

    @GetMapping("/{id}")
    public Medicamento getMedicamentoById(@PathVariable Long id) {
        return medicamentoService.getMedicamentoById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    @PostMapping
    public Medicamento createMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoService.createMedicamento(medicamento);
    }

    @PutMapping("/{id}")
    public Medicamento updateMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return medicamentoService.updateMedicamento(id, medicamento);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicamento(@PathVariable Long id) {
        medicamentoService.deleteMedicamento(id);
    }
}