package org.curso.spring.besttravel.api.controller;

import lombok.AllArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TicketRequest;
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
    public ResponseEntity<TicketResponse> getTicket(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@RequestBody TicketRequest ticketRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.update(ticketRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
