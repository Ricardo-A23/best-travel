package org.curso.spring.besttravel.api.models.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelResponse {

   private String name;
   private String address;
   private Integer rating;
   private BigDecimal price;
}
