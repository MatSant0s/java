package com.tcs.reto.repositories;

import com.tcs.reto.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    // Método para obtener todas las cuentas asociadas a un cliente por su ID
    List<Cuenta> findByClienteId(Long clienteId);
}
