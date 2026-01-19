package org.curso.spring.besttravel.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.curso.spring.besttravel.api.models.mappers.FlyMapper;
import org.curso.spring.besttravel.domain.entities.Fly;
import org.curso.spring.besttravel.domain.repository.FlyRepository;
import org.curso.spring.besttravel.infrastructure.abstract_service.IFlyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FlyServiceImpl implements IFlyService {

    private final FlyRepository flyRepository;
    private final FlyMapper flyMapper;

    @Override
    public Page<FlyResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return flyRepository.findAll(pageable).map(flyMapper::toResponse);
    }

    @Override
    public List<FlyResponse> selectLessPrice(BigDecimal price) {
        List<Fly> vuelos = flyRepository.selectLessPrice(price);
        return flyMapper.toResponseList(vuelos);
    }

    @Override
    public List<FlyResponse> selectBetweenPrice(BigDecimal priceMin, BigDecimal priceMax) {
        List<Fly> vuelos = flyRepository.selectBetweenPrice(priceMin, priceMax);
        return flyMapper.toResponseList(vuelos);
    }

    @Override
    public List<FlyResponse> selectOriginDestiny(String origin, String destiny) {
        List<Fly> vuelos = flyRepository.selectOriginDestiny(origin, destiny);
        return flyMapper.toResponseList(vuelos);
    }

    @Override
    public FlyResponse findByTicketId(UUID id) {
        Fly fly = flyRepository.findByTicketId(id)
                .orElseThrow(() -> new RuntimeException("Fly not found"));
        return flyMapper.toResponse(fly);
    }


}
