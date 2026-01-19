package org.curso.spring.besttravel.api.controller;


import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.HotelResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.IHotelService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final IHotelService hotelService;

    @GetMapping()
    public ResponseEntity<Page<HotelResponse>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return  ResponseEntity.ok(hotelService.getAll(page, size));
    }

    @GetMapping("/search/price-less")
    public ResponseEntity<List<HotelResponse>> findByPriceLessThan(@RequestParam BigDecimal price) {
        return ResponseEntity.ok(hotelService.findByPriceLessThan(price));
    }

    @GetMapping("/search/price-between")
    public ResponseEntity<List<HotelResponse>> findByPriceBetween(@RequestParam BigDecimal price1, @RequestParam BigDecimal price2) {
        return  ResponseEntity.ok(hotelService.findByPriceBetween(price1, price2));
    }

    @GetMapping("/search/rating-greater")
    public ResponseEntity<List<HotelResponse>> findByRatingGreaterThan(@RequestParam Integer rating) {
        return  ResponseEntity.ok(hotelService.findByRatingGreaterThan(rating));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<HotelResponse> findByReservationsId(@RequestParam UUID id) {
        return ResponseEntity.ok(hotelService.findByReservationsId(id));
    }
}
