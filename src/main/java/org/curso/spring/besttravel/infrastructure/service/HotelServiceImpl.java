package org.curso.spring.besttravel.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.response.HotelResponse;
import org.curso.spring.besttravel.api.models.mappers.HotelMapper;
import org.curso.spring.besttravel.domain.entities.Hotel;
import org.curso.spring.besttravel.domain.repository.HotelRepository;
import org.curso.spring.besttravel.infrastructure.abstract_service.IHotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<HotelResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return hotelRepository.findAll(pageable).
                map(hotelMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponse> findByPriceLessThan(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price must be greater than zero");
        }
        List<Hotel> hotels = hotelRepository.findByPriceLessThan(price);
        return hotelMapper.toResponseList(hotels);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponse> findByPriceBetween(BigDecimal price1, BigDecimal price2) {
        if (price1.compareTo(BigDecimal.ZERO) < 0 || price2.compareTo(BigDecimal.ZERO) < 0
        || price1.compareTo(price2) > 0) {
            throw new RuntimeException("Price must be greater than zero");
        }
        List<Hotel> hotels = hotelRepository.findByPriceBetween(price1, price2);
        return hotelMapper.toResponseList(hotels);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponse> findByRatingGreaterThan(Integer rating) {
        if (rating > 5 ||rating < 0) {
            throw new RuntimeException("Rating must be greater than zero");
        }
        List<Hotel> hotels = hotelRepository.findByRatingGreaterThan(rating);
        return hotelMapper.toResponseList(hotels);
    }

    @Override
    @Transactional(readOnly = true)
    public HotelResponse findByReservationsId(UUID id) {
        if(id == null) {
            throw new RuntimeException("Reservations id can't be null");
        }
        Hotel hotel =  hotelRepository.findByReservationsId(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return hotelMapper.toResponse(hotel);
    }
}
