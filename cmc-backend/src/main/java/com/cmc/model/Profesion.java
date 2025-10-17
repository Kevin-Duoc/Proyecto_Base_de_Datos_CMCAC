package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesion {
    
    private Integer codProfesion;
    private String nombreProfesion;
    private Integer nivelCriticidad;
}
