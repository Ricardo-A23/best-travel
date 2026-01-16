package org.curso.spring.besttravel.api.models.mappers;

import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.curso.spring.besttravel.domain.entities.Fly;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlyMapper {

    FlyResponse toResponse(Fly fly);
}
