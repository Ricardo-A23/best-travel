package org.curso.spring.besttravel.api.controller;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.curso.spring.besttravel.infrastructure.abstract_service.IFlyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
