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


@RequiredArgsConstructor
@Service
public class FlyServiceImpl implements IFlyService {

    private final FlyRepository flyRepository;
    private final FlyMapper  flyMapper;

    @Override
    public Page<FlyResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return flyRepository.findAll(pageable).map(flyMapper::toResponse);
    }

    @Override
    public FlyResponse findById(Long id) {
        Fly fly = flyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El vuelo no existe"));

        return flyMapper.toResponse(fly);
    }

}
