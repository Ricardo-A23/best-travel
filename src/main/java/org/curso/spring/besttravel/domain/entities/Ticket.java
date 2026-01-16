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
@Getter @Setter
@Builder
public class Ticket {

    @Id
    private UUID id;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;
    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fly_id")
    private Fly fly;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = true)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
