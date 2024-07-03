package com.generation.medicostos.service.implementation;

import com.generation.medicostos.dto.MedicamentoDTO;
import com.generation.medicostos.models.Farmacia;
import com.generation.medicostos.models.Medicamento;
import com.generation.medicostos.repository.FarmaciaRepository;
import com.generation.medicostos.repository.MedicamentoRepository;
import com.generation.medicostos.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImplementation implements MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Override
    public Medicamento saveMedicamento(MedicamentoDTO medicamentoDTO) {

        // Buscar farmacia por id
        Long farmaciaId = medicamentoDTO.getFarmaciaId();
        Optional<Farmacia> optionalPharmacy = farmaciaRepository.findById(farmaciaId);

        if (!optionalPharmacy.isPresent()) {
            throw new IllegalArgumentException("Farmacia con id " + farmaciaId + " no encontrada.");
        }

        Farmacia farmacia = optionalPharmacy.get();

        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setComplemento(medicamentoDTO.getComplemento());
        medicamento.setPrecio(medicamentoDTO.getPrecio());
        medicamento.setUrlImagen(medicamentoDTO.getUrlImagen());
        medicamento.setUrlMedicamento(medicamentoDTO.getUrlMedicamento());
        medicamento.setFarmacia(farmacia);

        return medicamentoRepository.save(medicamento);
    }

    @Override
    public List<Medicamento> getAllMedicamento() {
        return medicamentoRepository.findAll();
    }
}






