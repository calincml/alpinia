package com.alpinia.valoraseries.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerieResponse {
    private Integer id;
    private String nombre;
    private String caratula;
    private String plataforma;
    private String sinopsis;
    private BigDecimal valoracionMedia;
}

