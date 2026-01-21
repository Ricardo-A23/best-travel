package org.curso.spring.besttravel.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TourRequest;
import org.curso.spring.besttravel.api.models.dto.response.TourResponse;
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
    public ResponseEntity<TourResponse> getTourById(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TourResponse> createTour(@Valid @RequestBody TourRequest tourRequest) {

        TourResponse response = tourService.create(tourRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourResponse> updateTour(@PathVariable Long id, @Valid @RequestBody TourRequest updateRequest) {
        return ResponseEntity.ok(tourService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Tickets

    @PatchMapping("/{tourId}/tickets/{ticketId}")
    public ResponseEntity<TourResponse> addTicket(@PathVariable Long tourId, @PathVariable UUID ticketId) {
        return ResponseEntity.ok(tourService.addTicket(tourId, ticketId));
    }

    @DeleteMapping("/{tourId}/tickets/{ticketId}")
    public ResponseEntity<Void> removeTicket(@PathVariable Long tourId, @PathVariable UUID ticketId) {
        tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    //Reservations

    @PatchMapping("/{tourId}/reservations/{reservationId}")
    public ResponseEntity<TourResponse> addReservation(@PathVariable Long tourId, @PathVariable UUID reservationId) {
        return ResponseEntity.ok(tourService.addReservation(tourId, reservationId));
    }

    @DeleteMapping("/{tourId}/reservations/{reservationId}")
    public ResponseEntity<Void> removeReservation(@PathVariable Long tourId, @PathVariable UUID reservationId) {
        tourService.removeReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }




}
