package com.alpinia.valoraseries.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValoracionResponse {

    private Integer usuarioId;
    private Integer serieId;
    private Integer valoracion;
}
