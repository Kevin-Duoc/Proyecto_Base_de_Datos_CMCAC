package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auditor {

    private Long idAuditor;
    private Long numrun;
    private String dvrun;
    private Integer codComuna;
    private Integer codProfesion;
    private String appaterno;
    private String apmaterno;
    private String nombre;
    private Integer codEstcivil;
    private Integer puntaje;
    private Integer sueldo;
    private Integer codAfp;
    private Integer codIsapre;
    private Integer codTpcontrato;
    private Long numrunSup;
}
