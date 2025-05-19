package com.tcs.reto.services;

import com.tcs.reto.dto.MovimientoDto;
import java.util.List;

public interface MovimientoService {
    MovimientoDto save(MovimientoDto movimientoDto);
    MovimientoDto update(Long id, MovimientoDto movimientoDto);
    void delete(Long id);
    List<MovimientoDto> findAll();
    MovimientoDto findById(Long id);
}
