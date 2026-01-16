package org.curso.spring.besttravel.domain.repository;

import org.curso.spring.besttravel.domain.entities.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour,Long> {
}
