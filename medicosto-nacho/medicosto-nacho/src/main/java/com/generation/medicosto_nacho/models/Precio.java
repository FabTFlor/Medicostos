package com.generation.medicosto_nacho.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "precios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio;

    @ManyToOne
    private Medicamento medicamento;

    @ManyToOne
    private Farmacia farmacia;



}
