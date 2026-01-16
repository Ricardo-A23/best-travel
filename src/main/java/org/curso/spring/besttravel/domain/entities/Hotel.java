package org.curso.spring.besttravel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String address;

    private Integer rating;

    private BigDecimal price;

    @OneToMany(
            mappedBy = "hotel",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Reservation> reservations;
}
