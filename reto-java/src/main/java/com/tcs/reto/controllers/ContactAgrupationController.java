package com.tcs.reto.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.reto.bindings.ApiResponse;
import com.tcs.reto.services.ContactAgrupationService;

import lombok.RequiredArgsConstructor;

@RequestMapping("contact")
@RequiredArgsConstructor
@RestController
public class ContactAgrupationController {

	private final ContactAgrupationService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ApiResponse read() {
		var contacts = service.getAllContacts();

		return ApiResponse.builder().code(200).message("Create Contact").data(contacts).build();
	}

}
