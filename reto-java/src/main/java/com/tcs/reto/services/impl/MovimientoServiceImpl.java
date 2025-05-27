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

        // Validación de saldo disponible
        if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0 && saldoActual.add(valorMovimiento).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo no disponible");
        }

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

        movimientoExistente.setFecha(dto.getFecha());
        movimientoExistente.setTipoMovimiento(dto.getTipoMovimiento());
        movimientoExistente.setValor(dto.getValor());
        movimientoExistente.setCuenta(cuenta);

        if (dto.getSaldo() != null) {
            movimientoExistente.setSaldo(dto.getSaldo());
        }

        movimientoExistente = movimientoRepository.save(movimientoExistente);

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

    private Movimiento mapToEntity(MovimientoDto dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(dto.getId());
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
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