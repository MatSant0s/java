package com.tcs.reto.controllers;  // Asegúrate de que el paquete sea correcto

import com.tcs.reto.dto.ClienteDto;
import com.tcs.reto.entities.Cliente;
import com.tcs.reto.repositories.ClienteRepository;
import com.tcs.reto.services.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private ClienteDto clienteDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteDto = new ClienteDto();
        clienteDto.setId(1L);
        clienteDto.setNombre("Juan");
        clienteDto.setGenero("Masculino");
        clienteDto.setEdad(30);
        clienteDto.setIdentificacion("1234567890");
        clienteDto.setDireccion("Calle Falsa 123");
        clienteDto.setTelefono("987654321");
        clienteDto.setPassword("password123");
        clienteDto.setEstado(true);
    }

    @Test
    void testSave() {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setId(clienteDto.getId());
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setGenero(clienteDto.getGenero());
        clienteEntity.setEdad(clienteDto.getEdad());
        clienteEntity.setIdentificacion(clienteDto.getIdentificacion());
        clienteEntity.setDireccion(clienteDto.getDireccion());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setPassword(clienteDto.getPassword());
        clienteEntity.setEstado(clienteDto.getEstado());

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteEntity);

        ClienteDto savedCliente = clienteService.save(clienteDto);

        assertNotNull(savedCliente);
        assertEquals(clienteDto.getId(), savedCliente.getId());
        assertEquals(clienteDto.getNombre(), savedCliente.getNombre());
        assertEquals(clienteDto.getEstado(), savedCliente.getEstado());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdate() {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setId(clienteDto.getId());
        clienteEntity.setNombre("Juan");
        clienteEntity.setGenero("Masculino");
        clienteEntity.setEdad(30);
        clienteEntity.setIdentificacion("1234567890");
        clienteEntity.setDireccion("Calle Falsa 123");
        clienteEntity.setTelefono("987654321");
        clienteEntity.setPassword("password123");
        clienteEntity.setEstado(true);

        // Usar Optional.ofNullable para manejar posibles nulls
        when(clienteRepository.findById(clienteDto.getId())).thenReturn(Optional.ofNullable(clienteEntity));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteEntity);

        ClienteDto updatedCliente = clienteService.update(clienteDto.getId(), clienteDto);

        assertNotNull(updatedCliente);
        assertEquals(clienteDto.getId(), updatedCliente.getId());
        assertEquals(clienteDto.getNombre(), updatedCliente.getNombre());

        verify(clienteRepository, times(1)).findById(clienteDto.getId());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testFindById() {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setId(clienteDto.getId());
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setGenero(clienteDto.getGenero());
        clienteEntity.setEdad(clienteDto.getEdad());
        clienteEntity.setIdentificacion(clienteDto.getIdentificacion());
        clienteEntity.setDireccion(clienteDto.getDireccion());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setPassword(clienteDto.getPassword());
        clienteEntity.setEstado(clienteDto.getEstado());

        // Usar Optional.ofNullable para manejar posibles nulls
        when(clienteRepository.findById(clienteDto.getId())).thenReturn(Optional.ofNullable(clienteEntity));

        ClienteDto foundCliente = clienteService.findById(clienteDto.getId());

        assertNotNull(foundCliente);
        assertEquals(clienteDto.getId(), foundCliente.getId());
        assertEquals(clienteDto.getNombre(), foundCliente.getNombre());

        verify(clienteRepository, times(1)).findById(clienteDto.getId());
    }
}