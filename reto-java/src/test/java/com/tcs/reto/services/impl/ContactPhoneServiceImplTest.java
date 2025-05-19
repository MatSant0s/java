package com.tcs.reto.services.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.reto.repositories.ContactPhoneRepository;

@ExtendWith(MockitoExtension.class)
class ContactPhoneServiceImplTest {

	@Mock
	private ContactPhoneRepository repository;

	@InjectMocks
	private ContactPhoneServiceImpl service;

	@Test
	void testUpdate() {
		String numeroCelular = "09663654521";
		String numeroCuenta = "210658965412";

		when(repository.updateNumber(any(), any())).thenReturn(2);

		Exception ex = assertThrows(RuntimeException.class, () -> service.updateNumber(numeroCelular, numeroCuenta));

		assertNotNull(ex);
	}

	@Test
	void testDeleteWithException() {
		String clave = "546489749";

		when(repository.existsById(any())).thenReturn(false);

		Exception ex = assertThrows(RuntimeException.class, ()-> service.delete(clave));
		
		assertNotNull(ex);
	}
	
	@Test
	void testDelete() {
		String clave = "5783783";

		when(repository.existsById(any())).thenReturn(true);
		doNothing().when(repository).deleteById(any());

		 service.delete(clave);
		
		assertTrue(true);
	}

}
