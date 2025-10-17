package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    
    private Integer codEmpresa;
    private Integer codComuna;
    private Integer codSector;
    private String nombreEmpresa;
}
