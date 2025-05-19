package com.tcs.reto.dto;

import com.tcs.reto.enums.ContactTypeEnum;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactDto {

	private String name;

	private String nombreBanco;

	private String numeroCuenta;

	private ContactTypeEnum tipo;
}
