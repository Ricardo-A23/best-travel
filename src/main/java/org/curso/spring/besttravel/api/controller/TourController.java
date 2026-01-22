package org.curso.spring.besttravel.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TourRequest;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import org.curso.spring.besttravel.api.models.dto.response.TourResponse;
import org.curso.spring.besttravel.domain.exceptions.ResourceNotFoundException;
import org.curso.spring.besttravel.infrastructure.abstract_service.ITourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
public class TourController {

    private final ITourService tourService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar tour por id",
            description = "Obtiene la información de un tour a partir de su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tour encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TourResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un tour con el id proporcionado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<TourResponse> getTourById(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Crear tour",
            description = "Crea un nuevo tour con la información proporcionada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tour creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TourResponse.class)
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
    public ResponseEntity<TourResponse> createTour(
            @Valid @RequestBody TourRequest tourRequest
    ) {

        TourResponse response = tourService.create(tourRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar tour",
            description = "Actualiza la información de un tour existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tour actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TourResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tour no encontrado",
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
    public ResponseEntity<TourResponse> updateTour(
            @PathVariable Long id,
            @Valid @RequestBody TourRequest updateRequest
    ) {
        return ResponseEntity.ok(tourService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar tour",
            description = "Elimina un tour por su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Tour eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tour no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Tickets

    @PatchMapping("/{tourId}/tickets/{ticketId}")
    @Operation(
            summary = "Agregar ticket a un tour",
            description = "Asocia un ticket existente a un tour"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ticket agregado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TourResponse.class)
            )
    )
    public ResponseEntity<TourResponse> addTicket(
            @PathVariable Long tourId,
            @PathVariable UUID ticketId
    ) {
        return ResponseEntity.ok(tourService.addTicket(tourId, ticketId));
    }

    @DeleteMapping("/{tourId}/tickets/{ticketId}")
    @Operation(
            summary = "Eliminar ticket de un tour",
            description = "Desasocia un ticket de un tour"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Ticket eliminado correctamente"
    )
    public ResponseEntity<Void> removeTicket(
            @PathVariable Long tourId,
            @PathVariable UUID ticketId
    ) {
        tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    // Reservations

    @PatchMapping("/{tourId}/reservations/{reservationId}")
    @Operation(
            summary = "Agregar reservación a un tour",
            description = "Asocia una reservación existente a un tour"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Reservación agregada correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TourResponse.class)
            )
    )
    public ResponseEntity<TourResponse> addReservation(
            @PathVariable Long tourId,
            @PathVariable UUID reservationId
    ) {
        return ResponseEntity.ok(tourService.addReservation(tourId, reservationId));
    }

    @DeleteMapping("/{tourId}/reservations/{reservationId}")
    @Operation(
            summary = "Eliminar reservación de un tour",
            description = "Desasocia una reservación de un tour"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Reservación eliminada correctamente"
    )
    public ResponseEntity<Void> removeReservation(
            @PathVariable Long tourId,
            @PathVariable UUID reservationId
    ) {
        tourService.removeReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }

}
