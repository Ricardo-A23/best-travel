package org.curso.spring.besttravel.domain.repository;

import org.curso.spring.besttravel.domain.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    List<Hotel> findByPriceLessThan(BigDecimal price);

    List<Hotel> findByPriceBetween(BigDecimal price1, BigDecimal price2);

    List<Hotel> findByRatingGreaterThan(Integer rating);

    Optional<Hotel> findByReservationsId(UUID id);
}
