package com.tcs.reto.dto;

import lombok.Data;

@Data
public class PersonaDto {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
