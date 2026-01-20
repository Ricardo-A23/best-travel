package org.curso.spring.besttravel.api.models.mappers;

import org.curso.spring.besttravel.api.models.dto.response.TourResponse;
import org.curso.spring.besttravel.domain.entities.Tour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = ReservationMapper.class)
public interface TourMapper {

    TourResponse toResponse(Tour tour);
}
