package com.tcs.reto.controllers; 

import com.tcs.reto.dto.ClienteDto;
import com.tcs.reto.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteService clienteService;

    private ClienteDto clienteDto;

    @BeforeEach
    public void setUp() {
        // Configura los datos de prueba
        clienteDto = new ClienteDto();
        clienteDto.setNombre("Juan Pérez");
        clienteDto.setGenero("Masculino");
        clienteDto.setEdad(30);
        clienteDto.setIdentificacion("123456789");
        clienteDto.setDireccion("Calle Ficticia 123");
        clienteDto.setTelefono("555-1234");
        clienteDto.setPassword("password123");
        clienteDto.setEstado(true);
    }

    @Test
    public void testCreateCliente() {
        // Realiza una solicitud POST para crear un cliente
        ResponseEntity<ClienteDto> response = restTemplate.postForEntity("http://localhost:" + port + "/clientes", clienteDto, ClienteDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals(clienteDto.getNombre(), response.getBody().getNombre());
    }

    @Test
    public void testGetClienteById() {
        // Crear un cliente primero para usarlo en la prueba
        ClienteDto createdCliente = restTemplate.postForObject("http://localhost:" + port + "/clientes", clienteDto, ClienteDto.class);
        
        // Hacer la solicitud GET para obtener el cliente por ID
        ResponseEntity<ClienteDto> response = restTemplate.getForEntity("http://localhost:" + port + "/clientes/" + createdCliente.getId(), ClienteDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdCliente.getId(), response.getBody().getId());
        assertEquals(createdCliente.getNombre(), response.getBody().getNombre());
    }

    @Test
    public void testUpdateCliente() {
        // Crear un cliente primero para usarlo en la prueba
        ClienteDto createdCliente = restTemplate.postForObject("http://localhost:" + port + "/clientes", clienteDto, ClienteDto.class);

        // Modificar los datos del cliente
        createdCliente.setNombre("Juan Actualizado");
        createdCliente.setEdad(31);

        // Hacer la solicitud PUT para actualizar el cliente
        restTemplate.put("http://localhost:" + port + "/clientes/" + createdCliente.getId(), createdCliente);

        // Verificar que la actualización fue exitosa
        ResponseEntity<ClienteDto> response = restTemplate.getForEntity("http://localhost:" + port + "/clientes/" + createdCliente.getId(), ClienteDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan Actualizado", response.getBody().getNombre());
        assertEquals(31, response.getBody().getEdad());
    }

    @Test
    public void testDeleteCliente() {
        // Crear un cliente primero para usarlo en la prueba
        ClienteDto createdCliente = restTemplate.postForObject("http://localhost:" + port + "/clientes", clienteDto, ClienteDto.class);

        // Hacer la solicitud DELETE para eliminar el cliente
        restTemplate.delete("http://localhost:" + port + "/clientes/" + createdCliente.getId());

        // Verificar que el cliente ya no existe
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/clientes/" + createdCliente.getId(), String.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
