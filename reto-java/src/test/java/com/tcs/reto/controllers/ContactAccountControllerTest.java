package com.tcs.reto.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tcs.reto.services.ContactAccountService;

@AutoConfigureMockMvc
@WebMvcTest(ContactAccountController.class)
class ContactAccountControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ContactAccountService service;

	@Test
	void testDelete() throws Exception {
		doNothing().when(service).delete(any());

		mvc.perform(delete("/contact-account/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value(200))
		.andExpect(jsonPath("$.message").value("Delete Contact"));
	}

	@Test
	void testRead() throws Exception {
		when(service.findAll()).thenReturn(List.of());

		mvc.perform(get("/contact-account"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value(200))
		.andExpect(jsonPath("$.message").value("Get List"));
	}

	@Test
	void testUpdate() throws Exception {
		when(service.update(any(), any())).thenReturn(1);

		mvc.perform(put("/contact-account/1/123456789"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value(200))
		.andExpect(jsonPath("$.message").value("Update Account Number"));
	}

}
