package com.generation.medicosto_nacho.repository;

import com.generation.medicosto_nacho.models.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository  extends JpaRepository<Farmacia, Long> {
}
