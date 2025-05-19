package com.tcs.reto.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends PersonaDto {
    private Long id;
    private String password;
    private Boolean estado;
}
