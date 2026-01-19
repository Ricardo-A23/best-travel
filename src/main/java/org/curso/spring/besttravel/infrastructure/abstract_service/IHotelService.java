package org.curso.spring.besttravel.infrastructure.abstract_service;

import org.curso.spring.besttravel.api.models.dto.response.HotelResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IHotelService {

    Page<HotelResponse> getAll(Integer page, Integer size);

    List<HotelResponse> findByPriceLessThan(BigDecimal price);

    List<HotelResponse> findByPriceBetween(BigDecimal price1, BigDecimal price2);

    List<HotelResponse> findByRatingGreaterThan(Integer rating);

    HotelResponse findByReservationsId(UUID id);
}
