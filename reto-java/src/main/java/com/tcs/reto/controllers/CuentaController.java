package com.tcs.reto.controllers;

import com.tcs.reto.bindings.ApiResponse;
import com.tcs.reto.dto.CuentaDto;
import com.tcs.reto.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAll() {
        List<CuentaDto> cuentas = service.findAll();
        return ResponseEntity.ok(ApiResponse.builder().code(200).message("Lista de cuentas").data(cuentas).build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> create(@RequestBody CuentaDto dto) {
        CuentaDto created = service.save(dto);
        return new ResponseEntity<>(ApiResponse.builder().code(201).message("Cuenta creada").data(created).build(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id, @RequestBody CuentaDto dto) {
        try {
            CuentaDto updated = service.update(id, dto);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cuenta actualizada").data(updated).build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cuenta eliminada").build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getById(@PathVariable("id") Long id) {
        try {
            CuentaDto cuenta = service.findById(id);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cuenta encontrada").data(cuenta).build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }
}
