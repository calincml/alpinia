package com.alpinia.valoraseries.infrastructure.controller;

import com.alpinia.valoraseries.application.service.SerieService;
import com.alpinia.valoraseries.infrastructure.dto.request.SerieRequest;
import com.alpinia.valoraseries.infrastructure.dto.response.RankingResponse;
import com.alpinia.valoraseries.infrastructure.dto.response.SerieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
@Tag(name = "Series", description = "API para la gestión de series y sus valoraciones")
public class SerieController {

    private SerieService serieService;

    @Operation(summary = "Obtener el ranking de series", description = "Retorna una lista de series ordenadas por su valoración media")
    @ApiResponse(responseCode = "200", description = "Ranking obtenido exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RankingResponse.class)))
    @GetMapping("/ranking")
    public ResponseEntity<List<RankingResponse>> getRankingSeries() {
        return ResponseEntity.ok(serieService.getRankingSeries());
    }

    @Operation(summary = "Añadir una nueva serie", description = "Agrega una nueva serie a la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie añadida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SerieResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/add")
    public ResponseEntity<SerieResponse> addSerie(@RequestBody SerieRequest serie) {
        return ResponseEntity.ok(serieService.addSerie(serie));
    }

    @Operation(summary = "Actualizar una serie", description = "Actualiza los detalles de una serie existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SerieResponse.class))),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<SerieResponse> updateSerie(@Valid @PathVariable Integer id, @RequestBody SerieRequest request) {
        return ResponseEntity.ok(serieService.updateSerie(id, request));
    }

    @Operation(summary = "Modificar la carátula de una serie", description = "Actualiza la carátula de una serie existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carátula modificada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SerieResponse.class))),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PatchMapping(path = "/modificar_caratula/{id}")
    public ResponseEntity<SerieResponse> modifyCaratula(@PathVariable Integer id, @RequestParam String caratula) {
        return ResponseEntity.ok(serieService.modifyCaratula(id, caratula));
    }

    @Operation(summary = "Eliminar una serie", description = "Elimina una serie de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serie eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada")
    })
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.serieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

