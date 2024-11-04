package com.alpinia.valoraseries.infrastructure.controller;

import com.alpinia.valoraseries.application.service.ValoracionService;
import com.alpinia.valoraseries.infrastructure.dto.request.ValoracionRequest;
import com.alpinia.valoraseries.infrastructure.dto.response.ValoracionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoracion")
@AllArgsConstructor
@Tag(name = "Valoraciones", description = "API para la gestión de valoraciones de series") // Añadir Tag aquí
public class ValoracionController {

    private final ValoracionService valoracionService;

    @Operation(summary = "Obtener todas las valoraciones", description = "Retorna una lista de todas las valoraciones")
    @ApiResponse(responseCode = "200", description = "Valoraciones obtenidas exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ValoracionResponse.class)))
    @GetMapping("/all")
    public ResponseEntity<List<ValoracionResponse>> getAllValoraciones() {
        return ResponseEntity.ok(valoracionService.getAllValoraciones());
    }

    @Operation(summary = "Obtener valoraciones por ID de serie", description = "Retorna una lista de valoraciones de una serie específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoraciones obtenidas exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValoracionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada")
    })
    @GetMapping("/serie/{id}")
    public ResponseEntity<List<ValoracionResponse>> getValoracionesBySerieId(
            @Parameter(description = "ID de la serie") @Valid @PathVariable Integer id) {
        return ResponseEntity.ok(valoracionService.getValoracionesBySerieId(id));
    }

    @Operation(summary = "Obtener una valoración por ID", description = "Retorna una valoración específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoración obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValoracionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Valoración no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ValoracionResponse> getValoracionById(
            @Parameter(description = "ID de la valoración") @PathVariable Integer id) {
        return ResponseEntity.ok(valoracionService.getValoracionById(id));
    }

    @Operation(summary = "Añadir una valoración", description = "Agrega una nueva valoración para una serie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoración añadida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValoracionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/add")
    public ResponseEntity<ValoracionResponse> addValoracion(
            @Parameter(description = "Datos de la valoración a añadir") @RequestBody ValoracionRequest request) {
        return ResponseEntity.ok(valoracionService.addValoracion(request));
    }

    @Operation(summary = "Actualizar una valoración", description = "Actualiza una valoración existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valoración actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValoracionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Valoración no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/update")
    public ResponseEntity<ValoracionResponse> updateValoracion(
            @Parameter(description = "Datos de la valoración a actualizar") @RequestBody ValoracionRequest request) {
        return ResponseEntity.ok(valoracionService.updateValoracion(request));
    }

    @Operation(summary = "Eliminar una valoración", description = "Elimina una valoración específica usando ID de usuario y serie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Valoración eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Valoración no encontrada")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteValoracion(
            @Parameter(description = "ID del usuario") @RequestParam Integer userId,
            @Parameter(description = "ID de la serie") @RequestParam Integer serieId) {
        valoracionService.deleteValoracion(userId, serieId);
        return ResponseEntity.noContent().build();
    }
}
