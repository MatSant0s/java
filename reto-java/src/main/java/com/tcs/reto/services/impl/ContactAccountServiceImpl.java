package com.tcs.reto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.reto.dto.ContactDto;
import com.tcs.reto.enums.ContactTypeEnum;
import com.tcs.reto.repositories.ContactAccountRepository;
import com.tcs.reto.services.ContactAccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContactAccountServiceImpl implements ContactAccountService {

	private final ContactAccountRepository repository;

	@Override
	public void delete(Long pk) {
		log.info("Deleting contact account with pk: {}", pk);

		repository.deleteById(pk);
	}

	@Override
	public List<ContactDto> findAll() {
		log.info("Finding all contact accounts");

		return repository.findAll().stream()
				.map(contact -> ContactDto.builder().name(contact.getNombre()).nombreBanco(contact.getBanco())
						.numeroCuenta(contact.getNumero()).tipo(ContactTypeEnum.ACCOUNT).build())
				.toList();
	}

	@Override
	@Transactional
	public int update(Long pk, String numero) {
		log.info("Updating contact account with pk: {} and numero: {}", pk, numero);

		return repository.updateNumber(pk, numero);
	}
}
