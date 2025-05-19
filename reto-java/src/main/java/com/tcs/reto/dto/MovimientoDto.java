package com.tcs.reto.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MovimientoDto {

    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private BigDecimal valor;  // BigDecimal para valor
    private BigDecimal saldo;  // BigDecimal para saldo
    private Long cuentaId;
}
