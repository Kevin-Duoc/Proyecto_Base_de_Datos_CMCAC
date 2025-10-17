package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoContrato {
    
    private Integer codTpcontrato;
    private String nombreTpcontrato;
    private Integer porcIncentivo;
}
