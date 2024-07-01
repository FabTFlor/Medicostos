package com.generation.medicosto_nacho.service;

import com.generation.medicosto_nacho.models.Precio;
import com.generation.medicosto_nacho.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecioService {

    @Autowired
    private PrecioRepository precioRepository;

    public List<Precio> getAllPrecios() {
        return precioRepository.findAll();
    }

    public Optional<Precio> getPrecioById(Long id) {
        return precioRepository.findById(id);
    }

    public Precio createPrecio(Precio precio) {
        return precioRepository.save(precio);
    }

    public Precio updatePrecio(Long id, Precio precio) {
        return precioRepository.findById(id)
                .map(existingPrecio -> {
                    existingPrecio.setPrecio(precio.getPrecio());
                    existingPrecio.setMedicamento(precio.getMedicamento());
                    existingPrecio.setFarmacia(precio.getFarmacia());
                    return precioRepository.save(existingPrecio);
                })
                .orElseThrow(() -> new RuntimeException("Precio no encontrado"));
    }

    public void deletePrecio(Long id) {
        precioRepository.deleteById(id);
    }
}