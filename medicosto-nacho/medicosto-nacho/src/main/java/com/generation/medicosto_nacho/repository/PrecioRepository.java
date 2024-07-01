package com.generation.medicosto_nacho.repository;

import com.generation.medicosto_nacho.models.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long> {
}
