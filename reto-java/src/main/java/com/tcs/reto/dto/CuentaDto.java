package com.tcs.reto.dto;

import lombok.Data;

@Data
public class CuentaDto {
    
    private Long cuentaId;  // ID de la cuenta

    private Long clienteId;  // ID del cliente asociado

    private String numeroCuenta;  // Número de cuenta (unique)

    private String tipoCuenta;  // Tipo de cuenta

    private Double saldo;  // Saldo de la cuenta (correspondiente a "saldo_inicial")

    private Boolean estado;  // Estado de la cuenta (activo/inactivo)
}
