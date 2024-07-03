package com.generation.medicostos.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedicamentoDTO {
    private String nombre;
    private String complemento;
    private BigDecimal precio;
    private String urlImagen;
    private String urlMedicamento;
    private long farmaciaId;
    private String farmaciaNombre;
    private String farmaciaDireccion;
    private String farnaciaTelefono;
    private String farmaciaUrlImg;
    private String farmaciaUrlWeb;
}
