package com.tcs.reto.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CuentaDto {
    private Long cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;  
    private Boolean estado;
    private Long clienteId;
}
