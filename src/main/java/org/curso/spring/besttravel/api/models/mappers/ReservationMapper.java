package org.curso.spring.besttravel.api.models.mappers;

import org.curso.spring.besttravel.api.models.dto.request.ReservationRequest;
import org.curso.spring.besttravel.api.models.dto.response.ReservationResponse;
import org.curso.spring.besttravel.domain.entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponse toResponse(Reservation reservation);

    Reservation toEntity(ReservationRequest reservationRequest);
}