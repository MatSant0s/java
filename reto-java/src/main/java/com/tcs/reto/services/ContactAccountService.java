package com.tcs.reto.services;

import java.util.List;

import com.tcs.reto.dto.ContactDto;

public interface ContactAccountService {

	void delete(Long pk);

	List<ContactDto> findAll();

	int update(Long pk, String numero);
}
