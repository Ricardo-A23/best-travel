package org.curso.spring.besttravel.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.curso.spring.besttravel.api.models.dto.request.ReservationRequest;
import org.curso.spring.besttravel.api.models.dto.response.ReservationResponse;
import org.curso.spring.besttravel.api.models.mappers.ReservationMapper;
import org.curso.spring.besttravel.domain.entities.Customer;
import org.curso.spring.besttravel.domain.entities.Hotel;
import org.curso.spring.besttravel.domain.entities.Reservation;
import org.curso.spring.besttravel.domain.repository.CustumerRepository;
import org.curso.spring.besttravel.domain.repository.HotelRepository;
import org.curso.spring.besttravel.domain.repository.ReservationRepository;
import org.curso.spring.besttravel.infrastructure.abstract_service.IReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;
    private final HotelRepository hotelRepository;
    private final CustumerRepository custumerRepository;

    @Override
    @Transactional(readOnly = true)
    public ReservationResponse findById(UUID id) {
        return repository.findById(id).map(mapper::toResponse).orElseThrow(() -> new NoSuchElementException("reservacion no encontrada"));
    }

    @Override
    @Transactional
    public ReservationResponse create(ReservationRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("hotel no encontrada"));

        Customer customer = custumerRepository.findById(request.getCostumerId())
                .orElseThrow(() -> new RuntimeException("custumer no encontrada"));

        Reservation reservation = Reservation.builder()
                .id(UUID.randomUUID())
                .price(BigDecimal.valueOf(200))
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .dateTimeReservation(LocalDateTime.now())
                .price(hotel.getPrice().multiply(BigDecimal.valueOf(request.getTotalDays())))
                .customer(customer)
                .hotel(hotel)
                .build();
        return mapper.toResponse(repository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationResponse update(ReservationRequest reservationRequest, UUID id) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("reservacion no encontrada"));

        Hotel hotel = hotelRepository.findById(reservationRequest.getHotelId())
                .orElseThrow(() -> new RuntimeException("hotel no encontrada"));

        reservation.setDateTimeReservation(LocalDateTime.now());
        reservation.setDateStart(LocalDate.now());
        reservation.setDateEnd(LocalDate.now().plusDays(reservationRequest.getTotalDays()));
        reservation.setPrice(hotel.getPrice().multiply(BigDecimal.valueOf(reservationRequest.getTotalDays())));
        reservation.setHotel(hotel);

        log.info("Reservacion update Success with id {}", reservation.getId());
        return mapper.toResponse(repository.save(reservation));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if(!repository.existsById(id)) {
           throw new RuntimeException("reservacion no encontrada");
        }
        repository.deleteById(id);
    }
}
