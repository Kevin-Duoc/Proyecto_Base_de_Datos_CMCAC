package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorProceso {
    
    private Integer correlativo;
    private String sentenciaError;
    private String mensajeError;
}
