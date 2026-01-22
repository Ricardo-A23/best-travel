package org.curso.spring.besttravel.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.ReservationRequest;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import org.curso.spring.besttravel.api.models.dto.response.ReservationResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar reservación por id",
            description = "Obtiene la información de una reservación a partir de su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reservación encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reservación no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear reservación",
            description = "Crea una nueva reservación con la información proporcionada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Reservación creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<ReservationResponse> crearReservacion(
            @Valid @RequestBody ReservationRequest reservationRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(reservationRequest));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Modificar reservación",
            description = "Actualiza la información de una reservación existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reservación actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reservación no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<ReservationResponse> modificarReservacion(
            @Valid @RequestBody ReservationRequest reservationRequest,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(reservationService.update(reservationRequest, id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar reservación",
            description = "Elimina una reservación por su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Reservación eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reservación no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> eliminarReservacion(@PathVariable UUID id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
