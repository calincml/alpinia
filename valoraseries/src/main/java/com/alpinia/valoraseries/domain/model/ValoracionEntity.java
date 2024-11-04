package com.alpinia.valoraseries.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "valoracion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValoracionEntity {

    @EmbeddedId
    private ValoracionId id;

    @ManyToOne
    @MapsId("usuarioId") // Mapea usuarioId en ValoracionId
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @MapsId("serieId") // Mapea serieId en ValoracionId
    @JoinColumn(name = "serie_id", referencedColumnName = "id", nullable = false)
    private SerieEntity serie;

    @Column(nullable = false)
    private Integer valoracion;
}
