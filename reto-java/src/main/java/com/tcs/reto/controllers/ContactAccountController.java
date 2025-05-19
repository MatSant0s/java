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
import com.tcs.reto.services.ContactAccountService;

import lombok.RequiredArgsConstructor;

@RequestMapping("contact-account")
@RequiredArgsConstructor
@RestController
public class ContactAccountController {

	private final ContactAccountService service;

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "{pk}")
	public ApiResponse delete(@PathVariable("pk") Long pk) {
		service.delete(pk);

		return ApiResponse.builder().code(200).message("Delete Contact").build();
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ApiResponse read() {
		List<ContactDto> accounts = service.findAll();

		return ApiResponse.builder().code(200).message("Get List").data(accounts).build();
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, value = "{pk}/{numero}")
	public ApiResponse update(@PathVariable("pk") Long pk, @PathVariable("numero") String numero) {
		int rows = service.update(pk, numero);

		return ApiResponse.builder().code(200).message("Update Account Number").data(rows).build();
	}
}
