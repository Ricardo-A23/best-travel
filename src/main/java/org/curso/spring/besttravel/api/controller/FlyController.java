package org.curso.spring.besttravel.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.IFlyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlyController {

    private final IFlyService flyService;

    @Operation(
            summary = "Obtener vuelos paginados",
            description = "Devuelve una lista paginada de vuelos disponibles"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de vuelos obtenido correctamente"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parámetros de paginación inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Page<FlyResponse>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(flyService.getAll(page, size));
    }

    @Operation(
            summary = "Buscar vuelos por precio máximo",
            description = "Obtiene vuelos cuyo precio sea menor al indicado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelos encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Precio inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/price-menor")
    public ResponseEntity<List<FlyResponse>> findByLessPrice(@RequestParam BigDecimal price) {
        return ResponseEntity.ok(flyService.selectLessPrice(price));
    }

    @Operation(
            summary = "Buscar vuelos por rango de precio",
            description = "Obtiene vuelos cuyo precio esté dentro del rango especificado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelos encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Rango de precios inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/price-range")
    public ResponseEntity<List<FlyResponse>> findByBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(flyService.selectBetweenPrice(min, max));
    }

    @Operation(
            summary = "Buscar vuelos por ruta",
            description = "Obtiene vuelos filtrando por origen y destino"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelos encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ruta inválida",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/by-route")
    public ResponseEntity<List<FlyResponse>> findByOriginDestination(
            @RequestParam String origin,
            @RequestParam String destination
    ) {
        return ResponseEntity.ok(flyService.selectOriginDestiny(origin, destination));
    }

    @Operation(
            summary = "Buscar vuelo por ticket",
            description = "Obtiene el vuelo asociado a un ticket específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelo encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/{ticketId}")
    public ResponseEntity<FlyResponse> findByTicketId(@PathVariable UUID ticketId) {
        return ResponseEntity.ok(flyService.findByTicketId(ticketId));
    }
}
