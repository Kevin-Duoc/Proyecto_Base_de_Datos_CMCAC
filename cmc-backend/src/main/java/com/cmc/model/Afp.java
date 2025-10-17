package com.cmc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Afp {
    
    private Integer codAfp;
    private String nombreAfp;
    private Double porc; // Usamos Double para NUMBER(5,2)
}
