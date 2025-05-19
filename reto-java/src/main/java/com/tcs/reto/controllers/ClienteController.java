package com.tcs.reto.controllers;

import com.tcs.reto.bindings.ApiResponse;
import com.tcs.reto.dto.ClienteDto;
import com.tcs.reto.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAll() {
        List<ClienteDto> clientes = service.findAll();
        return ResponseEntity.ok(ApiResponse.builder().code(200).message("Lista de clientes").data(clientes).build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> create(@RequestBody ClienteDto dto) {
        ClienteDto created = service.save(dto);
        return new ResponseEntity<>(ApiResponse.builder().code(201).message("Cliente creado").data(created).build(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id, @RequestBody ClienteDto dto) {
        try {
            ClienteDto updated = service.update(id, dto);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cliente actualizado").data(updated).build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cliente eliminado").build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getById(@PathVariable("id") Long id) {
        try {
            ClienteDto cliente = service.findById(id);
            return ResponseEntity.ok(ApiResponse.builder().code(200).message("Cliente encontrado").data(cliente).build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().code(404).message(e.getMessage()).build());
        }
    }
}
