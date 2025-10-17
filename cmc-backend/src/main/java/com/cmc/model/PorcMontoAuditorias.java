package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PorcMontoAuditorias {
    
    private Integer montoAuditMin;
    private Integer montoAuditMax;
    private Double porcMontoAudit; // Usamos Double para NUMBER(4,2)
}
