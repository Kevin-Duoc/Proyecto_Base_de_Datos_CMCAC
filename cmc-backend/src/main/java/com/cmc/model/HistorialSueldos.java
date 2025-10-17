package com.cmc.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialSueldos {

    private Long idLog;
    private Long idAuditor;
    private Integer sueldoAnterior;
    private Integer sueldoNuevo;
    private String usuarioMod;
    private Date fechaMod;

}
