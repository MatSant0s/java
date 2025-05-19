package com.tcs.reto.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.reto.dto.ContactDto;
import com.tcs.reto.entities.ContactAccount;
import com.tcs.reto.repositories.ContactAccountRepository;

@ExtendWith(MockitoExtension.class)
class ContactAccountServiceImplTest {

	@InjectMocks
	private ContactAccountServiceImpl contactAccountService;

	@Mock
	private ContactAccountRepository repository;

	@Test
	void testDelete() {
		Long pk = 1L;
		doNothing().when(repository).deleteById(pk);

		contactAccountService.delete(pk);

		assertTrue(true);
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(ContactAccount.builder().build()));

		List<ContactDto> result = contactAccountService.findAll();

		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	void testUpdate() {
		when(repository.updateNumber(any(), any())).thenReturn(1);

		int rows = contactAccountService.update(123l, "2005698715");

		assertEquals(1, rows);
	}
}
