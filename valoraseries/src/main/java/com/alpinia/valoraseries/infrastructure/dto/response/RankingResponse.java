package com.alpinia.valoraseries.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingResponse {
    SerieResponse serie;
    List<UserVotoResponse> valoracion;
}
