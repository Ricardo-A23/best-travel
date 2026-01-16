package org.curso.spring.besttravel.api.models.mappers;

import org.curso.spring.besttravel.api.models.dto.request.TicketRequest;
import org.curso.spring.besttravel.api.models.dto.response.TicketResponse;
import org.curso.spring.besttravel.domain.entities.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //lo registramos como un beans de spring
public interface TicketMapper {

    TicketResponse toResponse(Ticket ticket);
    Ticket toEntity(TicketRequest ticketRequest);
}
