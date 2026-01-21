package org.curso.spring.besttravel.api.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketRequest {

    @NotBlank(message = "{ticket.customer-id.not-blank}")
    private String customerId;

    @NotNull(message = "{ticket.fly-id.not-null}")
    @Positive(message = "{ticket.fly-id.positive}")
    private Long flyId;
}
