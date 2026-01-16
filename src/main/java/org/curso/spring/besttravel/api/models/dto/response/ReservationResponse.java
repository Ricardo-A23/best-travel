package org.curso.spring.besttravel.api.models.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationResponse {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalDateTime dateTimeReservation;
    private HotelResponse hotel;
    private BigDecimal price;
}
