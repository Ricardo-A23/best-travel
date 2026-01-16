package org.curso.spring.besttravel.domain.repository;

import org.curso.spring.besttravel.domain.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
}
