package com.tcs.reto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "contactphone")
public class ContactPhone {

	@Id
	private String clave;

	private String nombreDelBanco;

	private String nombrePropietario;

	private String numeroCelular;

	private String numeroCuenta;
}
