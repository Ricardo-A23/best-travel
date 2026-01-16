package org.curso.spring.besttravel.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.curso.spring.besttravel.utils.enums.AeroLine;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Fly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_lat")
    private Double originLatitude;

    @Column(name = "origin_lng")
    private Double originLongitude;

    @Column(name = "destiny_lat")
    private Double destinationLatitude;

    @Column(name = "destiny_lng")
    private Double destinationLongitude;


    @Column(length = 20, name = "origin_name")
    private String originName;
    @Column(length = 20, name = "destiny_name")
    private String destinationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "aero_line")
    private AeroLine aeroLine;

    private BigDecimal price;

    @OneToMany(
            mappedBy = "fly",
            cascade = CascadeType.ALL, //operaciones
            orphanRemoval = true, //elimina los que no tengan relacion
            fetch = FetchType.LAZY
    )

    private Set<Ticket> tickets;
}
