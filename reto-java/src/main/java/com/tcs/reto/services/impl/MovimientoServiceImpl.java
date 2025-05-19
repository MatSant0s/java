package com.tcs.reto.services.impl;

import com.tcs.reto.dto.MovimientoDto;
import com.tcs.reto.entities.Movimiento;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.repositories.MovimientoRepository;
import com.tcs.reto.repositories.CuentaRepository;
import com.tcs.reto.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public MovimientoDto save(MovimientoDto dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + dto.getCuentaId() + " no encontrada"));

        BigDecimal saldoActual = cuenta.getSaldo() != null ? cuenta.getSaldo() : BigDecimal.ZERO;
        BigDecimal valorMovimiento = dto.getValor() != null ? dto.getValor() : BigDecimal.ZERO;
        BigDecimal nuevoSaldo = saldoActual.add(valorMovimiento);

        Movimiento movimiento = mapToEntity(dto);
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(nuevoSaldo);

        movimiento = movimientoRepository.save(movimiento);

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);

        return mapToDto(movimiento);
    }

    @Override
    public MovimientoDto update(Long id, MovimientoDto dto) {
        Movimiento movimientoExistente = movimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado"));

        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + dto.getCuentaId() + " no encontrada"));

        // Aquí puedes definir cómo quieres actualizar saldo, por ejemplo, no cambiarlo o recalcular
        movimientoExistente.setFecha(dto.getFecha());
        movimientoExistente.setTipoMovimiento(dto.getTipoMovimiento());
        movimientoExistente.setValor(dto.getValor());
        movimientoExistente.setCuenta(cuenta);

        // Si quieres actualizar saldo del movimiento, calcula o asigna según lógica
        // Por ejemplo, solo asignar saldo recibido (asegúrate que tenga sentido)
        if (dto.getSaldo() != null) {
            movimientoExistente.setSaldo(dto.getSaldo());
        }

        movimientoExistente = movimientoRepository.save(movimientoExistente);

        // Opcional: actualizar saldo de la cuenta si la lógica lo requiere
        // cuenta.setSaldo(...);
        // cuentaRepository.save(cuenta);

        return mapToDto(movimientoExistente);
    }

    @Override
    public void delete(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new NoSuchElementException("Movimiento con ID " + id + " no encontrado");
        }
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<MovimientoDto> findAll() {
        return movimientoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDto findById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado"));
        return mapToDto(movimiento);
    }

    // Mappers

    private Movimiento mapToEntity(MovimientoDto dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(dto.getId());
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        // No seteamos saldo aquí porque se calcula en save
        return movimiento;
    }

    private MovimientoDto mapToDto(Movimiento movimiento) {
        MovimientoDto dto = new MovimientoDto();
        dto.setId(movimiento.getId());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        dto.setCuentaId(movimiento.getCuenta() != null ? movimiento.getCuenta().getCuentaId() : null);
        return dto;
    }
}
