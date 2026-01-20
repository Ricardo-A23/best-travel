package org.curso.spring.besttravel.infrastructure.abstract_service;

import org.curso.spring.besttravel.api.models.dto.request.TourRequest;
import org.curso.spring.besttravel.api.models.dto.response.TourResponse;

import java.util.UUID;

public interface ITourService {


    TourResponse findById(Long tourId);
    TourResponse create(TourRequest tour);
    TourResponse update(Long tourId,TourRequest tour);
    void delete(Long tourId);

    TourResponse addTicket(Long tourId, UUID ticketId);
    TourResponse addReservation(Long tourId, UUID reservationId);
    void removeTicket(Long tourId, UUID ticketId);
    void removeReservation(Long tourId, UUID reservationId);
}
