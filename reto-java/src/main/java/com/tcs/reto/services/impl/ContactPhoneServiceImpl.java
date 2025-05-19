package com.tcs.reto.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.reto.dto.ContactDto;
import com.tcs.reto.entities.ContactPhone;
import com.tcs.reto.enums.ContactTypeEnum;
import com.tcs.reto.repositories.ContactPhoneRepository;
import com.tcs.reto.services.ContactPhoneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContactPhoneServiceImpl implements ContactPhoneService {

	private final ContactPhoneRepository repository;

	@Override
	public List<ContactDto> findAll() {
		log.info("Listando todos los contactos");

		List<ContactPhone> list = List.of();

		try {
			list = repository.findAll();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

		return list.stream().filter(contact -> Objects.nonNull(contact.getNombrePropietario()) && !contact.getNombrePropietario().isBlank())
				.map(contact -> ContactDto.builder().name(contact.getNombrePropietario())
						.nombreBanco(contact.getNombreDelBanco()).numeroCuenta(contact.getNumeroCuenta())
						.tipo(ContactTypeEnum.PHONE).build())
				.toList();
	}

	@Override
	public void delete(String clave) {
		log.info("Eliminando el la clave: {}", clave);

		boolean flag = repository.existsById(clave);

		if (flag) {
			repository.deleteById(clave);
		} else {
			log.error("El número ingresado no existe");

			throw new RuntimeException("El número ingresado no existe");
		}
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int updateNumber(String numeroCelular, String newNumero) {
		int flag = repository.updateNumber(numeroCelular, newNumero);

		if (flag >= 2) {
			log.error("Se actualizaron {} registros", flag);

			throw new RuntimeException(String.format("Se actualizaron %d registros", flag));
		}

		return flag;
	}
}
