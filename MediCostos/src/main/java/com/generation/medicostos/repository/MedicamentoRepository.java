package com.generation.medicostos.repository;

import com.generation.medicostos.models.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Page<Medicamento> findByNombreContainingIgnoreCaseOrComplementoContainingIgnoreCaseOrderByPrecioAsc(String nombre, String complemento, Pageable pageable);
}