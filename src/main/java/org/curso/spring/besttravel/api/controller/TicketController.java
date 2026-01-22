package org.curso.spring.besttravel.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TicketRequest;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import org.curso.spring.besttravel.api.models.dto.response.TicketResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.ITicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar ticket por id",
            description = "Obtiene la información de un ticket a partir de su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<TicketResponse> getTicket(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Crear ticket",
            description = "Crea un nuevo ticket con la información proporcionada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ticket creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)
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
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody TicketRequest ticketRequest
    ) {
        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Actualizar ticket",
            description = "Actualiza la información de un ticket existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ticket actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket no encontrado",
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
    public ResponseEntity<TicketResponse> updateTicket(
            @Valid @RequestBody TicketRequest ticketRequest,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(ticketService.update(ticketRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar ticket",
            description = "Elimina un ticket por su identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ticket eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
