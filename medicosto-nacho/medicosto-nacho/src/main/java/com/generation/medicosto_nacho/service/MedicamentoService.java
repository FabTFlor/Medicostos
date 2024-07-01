package com.generation.medicosto_nacho.service;


import com.generation.medicosto_nacho.models.Medicamento;
import com.generation.medicosto_nacho.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public Optional<Medicamento> getMedicamentoById(Long id) {
        return medicamentoRepository.findById(id);
    }

    public Medicamento createMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento updateMedicamento(Long id, Medicamento medicamento) {
        return medicamentoRepository.findById(id)
                .map(existingMedicamento -> {
                    existingMedicamento.setNombre(medicamento.getNombre());
                    existingMedicamento.setDescripcion(medicamento.getDescripcion());
                    return medicamentoRepository.save(existingMedicamento);
                })
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    public void deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}


