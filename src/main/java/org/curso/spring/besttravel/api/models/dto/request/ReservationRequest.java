package org.curso.spring.besttravel.api.models.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ReservationRequest {

    @NotBlank(message = "{reservation.customer-id.not-blank}")
    private String costumerId;

    @NotNull(message = "{reservation.hotel-id.not-null}")
    @Positive(message = "{reservation.hotel-id.positive}")
    private Long hotelId;

    @NotNull(message = "{reservation.total-days.not-null}")
    @Min(value = 1, message = "{reservation.total-days.min}")
    @Max(value = 30, message = "{reservation.total-days.max}")
    private Integer totalDays;
}
