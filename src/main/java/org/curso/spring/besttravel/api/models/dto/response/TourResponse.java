package org.curso.spring.besttravel.api.models.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TourResponse {

    private Long id;
    private List<TicketResponse> tickets;
    private List<ReservationResponse> reservations;
}
