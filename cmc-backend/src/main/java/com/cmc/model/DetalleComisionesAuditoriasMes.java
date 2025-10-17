package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleComisionesAuditoriasMes {
    
    private Integer mesProceso;
    private Integer annoProceso;
    private String runAuditor;
    private String nombreAuditor;
    private String nombreProfesion;
    private Integer comisionTotalAudit;
    private Integer comisionMontoAudit;
    private Integer comisionProfCritica;
    private Integer comisionExtra;
    private Integer totalComisionAudit;
    private Integer totalComisionEmpresa;
}