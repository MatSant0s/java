 package com.tcs.reto.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.reto.dto.ContactDto;
import com.tcs.reto.services.ContactAccountService;
import com.tcs.reto.services.ContactAgrupationService;
import com.tcs.reto.services.ContactPhoneService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContactAgrupationServiceImpl implements ContactAgrupationService {

	private final ContactAccountService contactAccountService;

	private final ContactPhoneService contactPhoneService;

	@Override
	public Map<Object, List<ContactDto>> getAllContacts() {
		List<ContactDto> phones = contactPhoneService.findAll();
		List<ContactDto> accounts = contactAccountService.findAll();

		List<ContactDto> contacts = new ArrayList<>();
		contacts.addAll(phones);
		contacts.addAll(accounts);

		return contacts.stream().sorted(Comparator.comparing(ContactDto::getName))
				.collect(Collectors.groupingBy(s -> s.getName().charAt(0)));
	}
}
