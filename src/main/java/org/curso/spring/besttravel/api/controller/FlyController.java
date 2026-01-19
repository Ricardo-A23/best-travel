package org.curso.spring.besttravel.api.controller;

import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ResponseEntity<Page<FlyResponse>> findAll(
            @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(flyService.getAll(page, size));
    }

    @GetMapping("/search/price-menor")
    public ResponseEntity<List<FlyResponse>> findByLessPrice(@RequestParam BigDecimal price) {
        return ResponseEntity.ok(flyService.selectLessPrice(price));
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<FlyResponse>> findByBetweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return ResponseEntity.ok(flyService.selectBetweenPrice(min, max));
    }

    @GetMapping("/search/by-route")
    public ResponseEntity<List<FlyResponse>> findByOriginDestination(@RequestParam String origin, @RequestParam String destination) {
        return ResponseEntity.ok(flyService.selectOriginDestiny(origin, destination));
    }

    @GetMapping("/search/{ticketId}")
    public ResponseEntity<FlyResponse> findByTicketId(@PathVariable UUID ticketId) {
        return ResponseEntity.ok(flyService.findByTicketId(id));
    }

}
