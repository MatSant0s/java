package com.tcs.reto.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean estado;
}
