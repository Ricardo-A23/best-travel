package org.curso.spring.besttravel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "customer")
public class Customer {

    @Id
    @Column(length = 12)
    private String dni;

    @Column(length = 50, name = "full_name")
    private String fullname;

    @Column(length = 20, name = "credit_card")
    private String creditCard;

    @Column(name = "total_flights")
    private Integer totalFlights ;

    @Column(name = "total_lodgings")
    private Integer totalLodgings;

    @Column(name = "total_tours")
    private Integer totalTours;

    @Column(length = 12, name = "phone_number")
    private String phoneNumber;

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true, //elimine si no tiene una relacion
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Reservation> reservations;

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Ticket> tickets;

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Tour> tours;


}
