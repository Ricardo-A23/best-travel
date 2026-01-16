package org.curso.spring.besttravel.api.controller;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.ReservationRequest;
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
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable UUID id){
       return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<ReservationResponse> crearReservacion(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.create(reservationRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> modificarReservacion(@RequestBody ReservationRequest reservationRequest,  @PathVariable UUID id){
        return ResponseEntity.ok(reservationService.update(reservationRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservacion(@PathVariable UUID id){
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
