package com.tcs.reto.services.impl;

import com.tcs.reto.dto.MovimientoDto;
import com.tcs.reto.entities.Movimiento;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.repositories.MovimientoRepository;
import com.tcs.reto.repositories.CuentaRepository;
import com.tcs.reto.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        // Obtener la cuenta desde el repositorio usando cuentaId del DTO
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + dto.getCuentaId() + " no encontrada"));

        // Crear el movimiento y asociar la cuenta
        Movimiento movimiento = mapToEntity(dto);
        movimiento.setCuenta(cuenta);  // Asociamos la cuenta al movimiento

        // Guardar el movimiento
        movimiento = movimientoRepository.save(movimiento);
        return mapToDto(movimiento);
    }

    @Override
    public MovimientoDto update(Long id, MovimientoDto dto) {
        // Buscar el movimiento existente
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado"));

        // Actualizar los campos del movimiento con los valores del DTO
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(dto.getSaldo());

        // Actualizar la cuenta asociada si es necesario
        if (dto.getCuentaId() != null) {
            Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                    .orElseThrow(() -> new NoSuchElementException("Cuenta con ID " + dto.getCuentaId() + " no encontrada"));
            movimiento.setCuenta(cuenta);  // Actualizamos la cuenta asociada
        }

        // Guardar el movimiento actualizado
        movimiento = movimientoRepository.save(movimiento);
        return mapToDto(movimiento);  // Retornamos el DTO actualizado
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

    // Mapper de DTO a entidad
    private Movimiento mapToEntity(MovimientoDto dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(dto.getId());
        movimiento.setFecha(dto.getFecha());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(dto.getSaldo());
        
        // Crear la relación con la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaId(dto.getCuentaId());  // Asignamos el ID de la cuenta al objeto cuenta
        movimiento.setCuenta(cuenta);  // Asignamos la cuenta al movimiento
        
        return movimiento;
    }

    // Mapper de entidad a DTO
    private MovimientoDto mapToDto(Movimiento movimiento) {
        MovimientoDto dto = new MovimientoDto();
        dto.setId(movimiento.getId());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        // Asignamos el ID de la cuenta en el DTO
        dto.setCuentaId(movimiento.getCuenta().getCuentaId());
        return dto;
    }
}
