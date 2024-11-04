package com.alpinia.valoraseries.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "serie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String caratula;
    private String plataforma;
    private String sinopsis;
    private BigDecimal valoracionMedia;
}
