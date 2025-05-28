package com.tcs.reto.services.impl;

import com.tcs.reto.dto.ClienteDto;
import com.tcs.reto.entities.Cliente;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.repositories.ClienteRepository;
import com.tcs.reto.repositories.CuentaRepository;
import com.tcs.reto.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public ClienteDto save(ClienteDto dto) {
        Cliente cliente = mapToEntity(dto);
        cliente = clienteRepository.save(cliente);
        return mapToDto(cliente);
    }

    @Override
    public ClienteDto update(Long id, ClienteDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + id + " no encontrado"));

        // Actualización Persona y Cliente
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setPassword(dto.getPassword());
        cliente.setEstado(dto.getEstado());

        cliente = clienteRepository.save(cliente);
        return mapToDto(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Buscar el cliente
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + id + " no encontrado"));

        // Obtener y eliminar las cuentas asociadas al cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(id); // Buscar cuentas por cliente ID
        for (Cuenta cuenta : cuentas) {
            cuentaRepository.delete(cuenta); // Eliminar cuenta
        }

        // Eliminar el cliente
        clienteRepository.delete(cliente); 
    }

    @Override
    public List<ClienteDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + id + " no encontrado"));
        return mapToDto(cliente);
    }

    // Maper de DTO a entidad
    private Cliente mapToEntity(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setPassword(dto.getPassword());
        cliente.setEstado(dto.getEstado());
        return cliente;
    }

    // Maper de entidad a DTO
    private ClienteDto mapToDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setPassword(cliente.getPassword());
        dto.setEstado(cliente.getEstado());
        return dto;
    }
}
