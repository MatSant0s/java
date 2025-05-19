package com.tcs.reto.services;

import com.tcs.reto.dto.CuentaDto;
import java.util.List;

public interface CuentaService {
    CuentaDto save(CuentaDto cuentaDto);
    CuentaDto update(Long id, CuentaDto cuentaDto);
    void delete(Long id);
    List<CuentaDto> findAll();
    CuentaDto findById(Long id);
}
