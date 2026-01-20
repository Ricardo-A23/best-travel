package org.curso.spring.besttravel.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TourRequest;
import org.curso.spring.besttravel.api.models.dto.response.TourResponse;
import org.curso.spring.besttravel.api.models.mappers.TourMapper;
import org.curso.spring.besttravel.domain.entities.*;
import org.curso.spring.besttravel.domain.repository.*;
import org.curso.spring.besttravel.infrastructure.abstract_service.ITourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements ITourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final CustumerRepository custumerRepository;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = true)
    public TourResponse findById(Long tourId) {
        return tourRepository.findById(tourId)
                .map(tourMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));
    }

    @Override
    @Transactional
    public TourResponse create(TourRequest tour) {
        Customer customer = custumerRepository
                .findById(tour.getCustomerId())
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));

        Tour tourCreate = Tour.builder()
                .customer(customer)
                .build();

        return tourMapper.toResponse(tourRepository.save(tourCreate));
    }

    @Override
    @Transactional
    public TourResponse update(Long tourId, TourRequest tour) {
        Customer customer = custumerRepository
                .findById(tour.getCustomerId())
                .orElseThrow(() -> new RuntimeException("El cliente no existe"));

        Tour tourUpdate = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));

        tourUpdate.setCustomer(customer);
        return tourMapper.toResponse(tourRepository.save(tourUpdate));
    }

    @Override
    @Transactional
    public void delete(Long tourId) {
        tourRepository.deleteById(tourId);
    }

    @Override
    @Transactional
    public TourResponse addTicket(Long tourId, UUID ticketId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("El ticket no existe"));

        tour.addTicket(ticket);
        return tourMapper.toResponse(tourRepository.save(tour));
    }

    @Override
    @Transactional
    public TourResponse addReservation(Long tourId, UUID reservationId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("El ticket no existe"));

        tour.addReservation(reservation);
        return  tourMapper.toResponse(tourRepository.save(tour));
    }

    @Override
    @Transactional
    public void removeTicket(Long tourId, UUID ticketId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));

        tour.removeTicket(ticketId);
    }

    @Override
    @Transactional
    public void removeReservation(Long tourId, UUID reservationId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("El tour no existe"));

        tour.removeReservation(reservationId);
    }
}
