package com.tcs.reto.services;

import java.util.List;
import java.util.Map;

import com.tcs.reto.dto.ContactDto;

public interface ContactAgrupationService {

	Map<Object, List<ContactDto>> getAllContacts();
}
