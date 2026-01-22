package org.curso.spring.besttravel.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import org.curso.spring.besttravel.api.models.dto.response.HotelResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.IHotelService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final IHotelService hotelService;

    @Operation(
            summary = "Obtener hoteles paginados",
            description = "Devuelve una lista paginada de hoteles disponibles"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de hoteles obtenido correctamente"),
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
    public ResponseEntity<Page<HotelResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(hotelService.getAll(page, size));
    }

    @Operation(
            summary = "Buscar hoteles por precio máximo",
            description = "Obtiene hoteles cuyo precio sea menor al indicado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Precio inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/price-less")
    public ResponseEntity<List<HotelResponse>> findByPriceLessThan(
            @RequestParam BigDecimal price
    ) {
        return ResponseEntity.ok(hotelService.findByPriceLessThan(price));
    }

    @Operation(
            summary = "Buscar hoteles por rango de precio",
            description = "Obtiene hoteles cuyo precio esté dentro del rango especificado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Rango de precios inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/price-between")
    public ResponseEntity<List<HotelResponse>> findByPriceBetween(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(hotelService.findByPriceBetween(min, max));
    }

    @Operation(
            summary = "Buscar hoteles por rating mínimo",
            description = "Obtiene hoteles con un rating mayor o igual al indicado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Rating inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/rating-greater")
    public ResponseEntity<List<HotelResponse>> findByRatingGreaterThan(@RequestParam Integer rating) {
        return ResponseEntity.ok(hotelService.findByRatingGreaterThan(rating));
    }

    @Operation(
            summary = "Buscar hotel por reservación",
            description = "Obtiene el hotel asociado a una reservación específica"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reservación no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search/{id}")
    public ResponseEntity<HotelResponse> findByReservationsId(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.findByReservationsId(id));
    }
}
