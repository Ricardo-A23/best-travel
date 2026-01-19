package org.curso.spring.besttravel.infrastructure.abstract_service;


import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.curso.spring.besttravel.domain.entities.Fly;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFlyService {

    Page<FlyResponse> getAll(int  page, int size);

    List<FlyResponse> selectLessPrice(BigDecimal price);

    List<FlyResponse> selectBetweenPrice(BigDecimal priceMin, BigDecimal priceMax);

    List<FlyResponse> selectOriginDestiny(String origin, String destiny);

    FlyResponse findByTicketId(UUID id);

}
