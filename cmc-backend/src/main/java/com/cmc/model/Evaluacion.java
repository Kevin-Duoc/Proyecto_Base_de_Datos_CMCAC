package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluacion {
    
    private String evaStatus; // Usamos String para CHAR(1)
    private Integer evaPuntajeMin;
    private Integer evaPuntajeMax;
    private Integer porcentaje;
}
