package com.alpinia.valoraseries.infrastructure.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ValoracionRequest {

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Integer usuarioId;

    @NotNull(message = "El ID de la serie no puede ser nulo")
    private Integer serieId;

    @NotNull(message = "La valoración no puede ser nula")
    @Min(value = 1, message = "La valoración debe ser al menos 1")
    private Integer valoracion;
}
