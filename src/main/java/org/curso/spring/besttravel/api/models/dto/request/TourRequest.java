package org.curso.spring.besttravel.api.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TourRequest {

    @NotBlank(message = "{tour.customer-id.not-blank}")
    private String customerId;
}
