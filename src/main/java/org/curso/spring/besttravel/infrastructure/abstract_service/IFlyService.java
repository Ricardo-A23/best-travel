package org.curso.spring.besttravel.infrastructure.abstract_service;


import org.curso.spring.besttravel.api.models.dto.response.FlyResponse;
import org.springframework.data.domain.Page;

public interface IFlyService {

    Page<FlyResponse> getAll(int  page, int size);

    FlyResponse findById(Long id );

}
