package com.cmc.model;

import java.util.Date; // Importante para manejar las fechas
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auditoria {
    
    private Long idAuditor;
    private Integer codEmpresa;
    private Integer montoAuditoria;
    private Date inicioAuditoria;
    private Date finAuditoria;
}
