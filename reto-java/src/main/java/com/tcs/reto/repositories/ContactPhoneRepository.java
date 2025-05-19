package com.tcs.reto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.reto.entities.ContactPhone;

public interface ContactPhoneRepository extends JpaRepository<ContactPhone, String> {

	@Modifying
	@Query(value = "UPDATE ContactPhone a SET a.numeroCuenta = :newNumero WHERE a.numeroCelular = :numeroCelular")
	int updateNumber(@Param("numeroCelular") String numeroCelular, @Param("newNumero") String newNumero);
}
