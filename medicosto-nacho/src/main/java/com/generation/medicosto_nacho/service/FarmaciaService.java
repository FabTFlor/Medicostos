package com.generation.medicosto_nacho.service;

import com.generation.medicosto_nacho.models.Farmacia;
import com.generation.medicosto_nacho.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public List<Farmacia> getAllFarmacias() {
        return farmaciaRepository.findAll();
    }

    public Optional<Farmacia> getFarmaciaById(Long id) {
        return farmaciaRepository.findById(id);
    }

    public Farmacia createFarmacia(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

    public Farmacia updateFarmacia(Long id, Farmacia farmacia) {
        return farmaciaRepository.findById(id)
                .map(existingFarmacia -> {
                    existingFarmacia.setNombre(farmacia.getNombre());
                    existingFarmacia.setDireccion(farmacia.getDireccion());
                    existingFarmacia.setTelefono(farmacia.getTelefono());
                    existingFarmacia.setUrl(farmacia.getUrl());
                    return farmaciaRepository.save(existingFarmacia);
                })
                .orElseThrow(() -> new RuntimeException("Farmacia no encontrada"));
    }

    public void deleteFarmacia(Long id) {
        farmaciaRepository.deleteById(id);
    }
}