package com.alpinia.valoraseries.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "usuario")
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String password;
}
