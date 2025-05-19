package com.tcs.reto.services.impl;

import com.tcs.reto.dto.CuentaDto;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.entities.Cliente;
import com.tcs.reto.repositories.CuentaRepository;
import com.tcs.reto.repositories.ClienteRepository;
import com.tcs.reto.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public CuentaDto save(CuentaDto dto) {
        // Obtener el cliente desde el repositorio
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + dto.getClienteId() + " no encontrado"));

        // Crear la cuenta y asociar el cliente
        Cuenta cuenta = mapToEntity(dto);
        cuenta.setCliente(cliente);  // Asociamos el cliente a la cuenta

        cuenta = cuentaRepository.save(cuenta);
        return mapToDto(cuenta);
    }

    @Override
    public CuentaDto update(Long id, CuentaDto dto) {
        // Buscar cuenta existente
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + id + " no encontrada"));

        // Actualizar datos de cuenta
        cuenta.setTipoCuenta(dto.getTipoCuenta());
        cuenta.setSaldo(dto.getSaldo());  // 'saldo' de DTO se mapea a 'saldo' en la entidad
        cuenta.setEstado(dto.getEstado());

        // Actualizar cliente si es necesario
        // No necesitamos actualizar el cliente si la relación ManyToOne está bien definida.
        if (cuenta.getCliente() == null || !cuenta.getCliente().getId().equals(dto.getClienteId())) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + dto.getClienteId() + " no encontrado"));
            cuenta.setCliente(cliente);  // Actualizamos el cliente asociado
        }

        cuenta = cuentaRepository.save(cuenta);
        return mapToDto(cuenta);
    }

    @Override
    public void delete(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new NoSuchElementException("Cuenta con ID " + id + " no encontrada");
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<CuentaDto> findAll() {
        return cuentaRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto findById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + id + " no encontrada"));
        return mapToDto(cuenta);
    }

    // Mapper de DTO a entidad
    private Cuenta mapToEntity(CuentaDto dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaId(dto.getCuentaId());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(dto.getTipoCuenta());
        cuenta.setSaldo(dto.getSaldo());  // 'saldo' en DTO debe ser mapeado a 'saldo' en la entidad
        cuenta.setEstado(dto.getEstado());
        
        // Se asocia el cliente, pero no se usa clienteId directamente.
        Cliente cliente = new Cliente();
        cliente.setId(dto.getClienteId());  // Seteamos el cliente por ID
        cuenta.setCliente(cliente);  // Asociamos el cliente a la cuenta
        
        return cuenta;
    }

    // Mapper de entidad a DTO
    private CuentaDto mapToDto(Cuenta cuenta) {
        CuentaDto dto = new CuentaDto();
        dto.setCuentaId(cuenta.getCuentaId());
        // No necesitamos un clienteId directo, lo tomamos de la relación Cliente
        dto.setClienteId(cuenta.getCliente() != null ? cuenta.getCliente().getId() : null);
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldo(cuenta.getSaldo());
        dto.setEstado(cuenta.getEstado());
        return dto;
    }
}
