package com.alpinia.valoraseries.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SerieRequest {

    @NotBlank(message = "El nombre de la serie es obligatorio")
    @Size(max = 100, message = "El nombre de la serie no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La carátula es obligatoria")
    private String caratula;

    @NotBlank(message = "La plataforma es obligatoria")
    @Size(max = 100, message = "La plataforma no puede superar los 100 caracteres")
    private String plataforma;

    @NotBlank(message = "La sinopsis es obligatoria")
    private String sinopsis;

    private BigDecimal valoracionMedia;
}
