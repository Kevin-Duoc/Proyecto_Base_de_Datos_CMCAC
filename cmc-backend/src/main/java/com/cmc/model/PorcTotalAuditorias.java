package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PorcTotalAuditorias {
    
    private Integer totalAuditMin;
    private Integer totalAuditMax;
    private Double porcTotalAudit; // Usamos Double para NUMBER(4,2)
}
