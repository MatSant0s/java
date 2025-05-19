package com.tcs.reto.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tcs.reto.services.ContactPhoneService;

@AutoConfigureMockMvc
@WebMvcTest(ContactPhoneController.class)
class ContactPhoneControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ContactPhoneService service;

	@Test
	void read() throws Exception {
		when(service.findAll()).thenReturn(List.of());

		mvc.perform(get("/contact-phone")).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(200))
				.andExpect(jsonPath("$.message").value("Get List"));
	}

}
