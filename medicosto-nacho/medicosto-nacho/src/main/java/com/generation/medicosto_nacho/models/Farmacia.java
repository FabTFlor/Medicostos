package com.generation.medicosto_nacho.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farmacias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Farmacia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}