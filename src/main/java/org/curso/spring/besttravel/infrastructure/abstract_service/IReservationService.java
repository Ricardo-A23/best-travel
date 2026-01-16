package org.curso.spring.besttravel.infrastructure.abstract_service;

import org.curso.spring.besttravel.api.models.dto.request.ReservationRequest;
import org.curso.spring.besttravel.api.models.dto.response.ReservationResponse;

import java.util.UUID;

public interface IReservationService {

    ReservationResponse findById(UUID id );

    ReservationResponse create(ReservationRequest request);

    ReservationResponse update(ReservationRequest request, UUID id);

    void deleteById(UUID id);

}
