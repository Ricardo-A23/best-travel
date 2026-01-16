package org.curso.spring.besttravel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "tour",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @Builder.Default  // Para que Lombok use esta inicialización en el builder
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(
            mappedBy = "tour",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @Builder.Default
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;


    public void addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket no puede ser nulo");
        }
        tickets.add(ticket);
        ticket.setTour(this);
    }

    public void removeTicket(UUID id) {
        if (id != null) {
            tickets.stream()
                    .filter(ticket -> ticket.getId().equals(id))
                    .findFirst()
                    .ifPresent(ticket -> {
                        tickets.remove(ticket);
                        ticket.setTour(null);
                    });
        }
    }

    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation no puede ser nula");
        }
        reservations.add(reservation);
        reservation.setTour(this);
    }

    public void removeReservation(UUID id) {
        if (id != null) {
            reservations.stream()
                    .filter(reservation -> reservation.getId().equals(id))
                    .findFirst()
                    .ifPresent(reservation -> {
                        reservations.remove(reservation);
                        reservation.setTour(null);
                    });
        }
    }
}

