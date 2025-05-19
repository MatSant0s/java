package com.tcs.reto.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tcs.reto.bindings.ApiResponse;
import com.tcs.reto.dto.MovimientoDto;
import com.tcs.reto.services.MovimientoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getAll() {
        List<MovimientoDto> lista = service.findAll();
        return ApiResponse.builder().code(200).message("Lista de movimientos").data(lista).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getById(@PathVariable ("id")Long id) {
        MovimientoDto dto = service.findById(id);
        return ApiResponse.builder()
                .code(200)
                .message("Movimiento encontrado")
                .data(dto)
                .build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse create(@RequestBody MovimientoDto dto) {
        MovimientoDto saved = service.save(dto);
        return ApiResponse.builder().code(201).message("Movimiento creado").data(saved).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable Long id, @RequestBody MovimientoDto dto) {
        MovimientoDto updated = service.update(id, dto);
        return ApiResponse.builder().code(200).message("Movimiento actualizado").data(updated).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.builder().code(200).message("Movimiento eliminado").build();
    }
}
