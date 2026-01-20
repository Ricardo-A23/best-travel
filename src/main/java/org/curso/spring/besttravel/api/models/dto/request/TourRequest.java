package org.curso.spring.besttravel.api.models.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TourRequest {

    private String customerId;
}
