package org.curso.spring.besttravel.domain.repository;

import org.curso.spring.besttravel.domain.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
}
