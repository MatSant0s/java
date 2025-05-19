package com.tcs.reto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactaccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactAccount {

    @Id
    private Long pk;

    private String banco;
    private String nombre;
    private String numero;
    private String correo; 
}
