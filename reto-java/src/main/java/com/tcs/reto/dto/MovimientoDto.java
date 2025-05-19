package com.tcs.reto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovimientoDto {
    
    private Long id;  // ID del movimiento

    private Date fecha;  // Fecha del movimiento

    private String tipoMovimiento;  // Tipo de movimiento (depósito, retiro, etc.)

    private Double valor;  // Valor del movimiento

    private Double saldo;  // Saldo después del movimiento

    private Long cuentaId;  // ID de la cuenta asociada al movimiento
}
