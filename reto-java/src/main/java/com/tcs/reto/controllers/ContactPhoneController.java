package com.tcs.reto.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.reto.bindings.ApiResponse;
import com.tcs.reto.dto.ContactDto;
import com.tcs.reto.services.ContactPhoneService;

import lombok.RequiredArgsConstructor;

@RequestMapping("contact-phone")
@RequiredArgsConstructor
@RestController
public class ContactPhoneController {

	private final ContactPhoneService service;

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "{phoneNumber}")
	public ApiResponse delete(@PathVariable("phoneNumber") String phoneNumber) {
		try {
			service.delete(phoneNumber);
			return ApiResponse.builder().code(200).message("Delete Contact").build();
		} catch (Exception ex) {
			return ApiResponse.builder().code(500).message(ex.getMessage()).build();
		}
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ApiResponse read() {
		List<ContactDto> phones = service.findAll();

		return ApiResponse.builder().code(200).message("Get List").data(phones).build();
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "{numeroCelular}/{numero}")
	public ApiResponse update(@PathVariable("numeroCelular") String numeroCelular,
			@PathVariable("numero") String numero) {
		int rows = service.updateNumber(numeroCelular, numero);

		return ApiResponse.builder().code(200).message("Update Phone Account").data(rows).build();
	}

}
