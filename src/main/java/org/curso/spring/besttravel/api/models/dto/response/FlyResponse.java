package org.curso.spring.besttravel.api.models.dto.response;

import lombok.*;
import org.curso.spring.besttravel.utils.enums.AeroLine;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FlyResponse {

    private Long id;

    private Double originLatitude;

    private Double originLongitude;

    private Double destinationLatitude;

    private Double destinationLongitude;

    private String originName;

    private String destinationName;

    private AeroLine aeroLine;
}
