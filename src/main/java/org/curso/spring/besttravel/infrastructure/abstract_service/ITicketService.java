package org.curso.spring.besttravel.infrastructure.abstract_service;

import org.curso.spring.besttravel.api.models.dto.request.TicketRequest;
import org.curso.spring.besttravel.api.models.dto.response.TicketResponse;

import java.util.UUID;

public interface ITicketService {

    TicketResponse findById(UUID id );

    TicketResponse create(TicketRequest request);

    TicketResponse update(TicketRequest request, UUID id);

    void deleteById(UUID id);

}
