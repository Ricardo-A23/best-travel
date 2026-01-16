package org.curso.spring.besttravel.domain.repository;


import org.curso.spring.besttravel.domain.entities.Fly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlyRepository extends JpaRepository<Fly, Long> {

    //:price debe hacer referencia al mismo nombre del parametro
    @Query("SELECT f FROM Fly f WHERE f.price < :price")
    List<Fly> selectLessPrice(BigDecimal price);

    @Query("SELECT f FROM Fly f WHERE f.price BETWEEN :priceMin AND :priceMax")
    List<Fly> selectBetweenPrice(BigDecimal priceMin, BigDecimal priceMax);

    @Query("SELECT f FROM Fly f WHERE f.originName = :origin and f.destinationName = :destiny")
    List<Fly> selectOriginDestiny(String origin, String destiny);

    @Query("SELECT f FROM Fly f JOIN FETCH Ticket t WHERE t.id = :id")
    Optional<Fly> findByTicketId(UUID id);

}
