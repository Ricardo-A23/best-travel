package org.curso.spring.besttravel.api.models.mappers;

import org.curso.spring.besttravel.api.models.dto.response.HotelResponse;
import org.curso.spring.besttravel.domain.entities.Hotel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelResponse toResponse(Hotel hotel);
    Hotel toEntity (HotelResponse hotelResponse);
    List<HotelResponse> toResponseList(List<Hotel> hotels);
}
