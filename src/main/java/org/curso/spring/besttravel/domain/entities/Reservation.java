package org.curso.spring.besttravel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {

    @Id
    private UUID id;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    @Column(name = "date_reservation")
    private LocalDateTime dateTimeReservation;

    private Integer totalDays;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = true)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

