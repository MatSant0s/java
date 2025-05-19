package com.tcs.reto.services;

import com.tcs.reto.dto.ClienteDto;
import java.util.List;

public interface ClienteService {
    ClienteDto save(ClienteDto clienteDto);
    ClienteDto update(Long id, ClienteDto clienteDto);
    void delete(Long id);
    List<ClienteDto> findAll();
    ClienteDto findById(Long id);
}
