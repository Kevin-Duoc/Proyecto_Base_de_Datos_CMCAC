package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenComisionesAuditoriasMes {
    
    private Integer mesProceso;
    private Integer annoProceso;
    private String nombreProfesion;
    private Integer totalAuditores;
    private Integer totalConAuditorias;
    private Integer totalSinAuditorias;
    private Long montoTotalAuditorias; // Usamos Long por si el monto es muy grande
    private Long montoTotalComisiones; // Usamos Long por si el monto es muy grande
}