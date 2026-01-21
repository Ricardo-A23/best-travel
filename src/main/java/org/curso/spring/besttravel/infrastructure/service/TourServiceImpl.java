package org.curso.spring.besttravel.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.curso.spring.besttravel.api.models.dto.request.TourRequest;
import org.curso.spring.besttravel.api.models.dto.response.TourResponse;
import org.curso.spring.besttravel.api.models.mappers.TourMapper;
import org.curso.spring.besttravel.domain.entities.*;
import org.curso.spring.besttravel.domain.exceptions.ResourceNotFoundException;
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
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = true)
    public TourResponse findById(Long tourId) {
        Tour tour = validTour(tourId);
        return tourMapper.toResponse(tour);
    }

    @Override
    @Transactional
    public TourResponse create(TourRequest tour) {
        Customer customer = customerRepository
                .findById(tour.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", tour.getCustomerId()));

        Tour tourCreate = Tour.builder()
                .customer(customer)
                .build();

        return tourMapper.toResponse(tourRepository.save(tourCreate));
    }

    @Override
    @Transactional
    public TourResponse update(Long tourId, TourRequest tour) {
        Customer customer = customerRepository
                .findById(tour.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", tour.getCustomerId()));

        Tour tourUpdate = validTour(tourId);
        tourUpdate.setCustomer(customer);
        return tourMapper.toResponse(tourRepository.save(tourUpdate));
    }

    @Override
    @Transactional
    public void delete(Long tourId) {
        Tour tour = validTour(tourId);
        tourRepository.delete(tour);
    }

    @Override
    @Transactional
    public TourResponse addTicket(Long tourId, UUID ticketId) {
        Tour tour = validTour(tourId);
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", ticketId));

        tour.addTicket(ticket);
        return tourMapper.toResponse(tourRepository.save(tour));
    }

    @Override
    @Transactional
    public TourResponse addReservation(Long tourId, UUID reservationId) {
        Tour tour = validTour(tourId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", reservationId));

        tour.addReservation(reservation);
        return  tourMapper.toResponse(tourRepository.save(tour));
    }

    @Override
    @Transactional
    public void removeTicket(Long tourId, UUID ticketId) {
        Tour tour = validTour(tourId);
        tour.removeTicket(ticketId);
    }

    @Override
    @Transactional
    public void removeReservation(Long tourId, UUID reservationId) {
        Tour tour = validTour(tourId);
        tour.removeReservation(reservationId);
    }

    @Transactional(readOnly = true)
    public Tour validTour(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour", tourId));
    }
}
