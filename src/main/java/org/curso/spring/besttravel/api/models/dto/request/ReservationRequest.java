package org.curso.spring.besttravel.api.models.dto.request;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ReservationRequest {
    private String costumerId;
    private Long hotelId;
    private Integer totalDays;
}
