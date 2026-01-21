package org.curso.spring.besttravel.infrastructure.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.curso.spring.besttravel.api.models.dto.request.TicketRequest;
import org.curso.spring.besttravel.api.models.dto.response.TicketResponse;
import org.curso.spring.besttravel.api.models.mappers.TicketMapper;
import org.curso.spring.besttravel.domain.entities.Customer;
import org.curso.spring.besttravel.domain.entities.Fly;
import org.curso.spring.besttravel.domain.entities.Ticket;
import org.curso.spring.besttravel.domain.exceptions.ResourceNotFoundException;
import org.curso.spring.besttravel.domain.repository.CustomerRepository;
import org.curso.spring.besttravel.domain.repository.FlyRepository;
import org.curso.spring.besttravel.domain.repository.TicketRepository;
import org.curso.spring.besttravel.infrastructure.abstract_service.ITicketService;
import org.curso.spring.besttravel.utils.FechasUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional(readOnly = true)
    public TicketResponse findById(UUID id) {
        return ticketMapper.toResponse(ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id.toString())));
    }

    @Override
    @Transactional
    public TicketResponse create(TicketRequest ticketRequest) {
        Customer customer = customerRepository.findById(ticketRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Costumer",  ticketRequest.getCustomerId()));
        Fly fly = flyRepository.findById(ticketRequest.getFlyId())
                .orElseThrow(() -> new ResourceNotFoundException("Fly",   ticketRequest.getFlyId()));

        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID())
                .customer(customer)
                .fly(fly)
                .price(fly.getPrice().add(fly.getPrice().multiply(BigDecimal.valueOf(0.25))))
                .purchaseDate(LocalDate.now())
                .departureDate(FechasUtil.getRamdomSoon())
                .arrivalDate(FechasUtil.getRamdomLatter())
                .build();

        TicketResponse ticketResponse = ticketMapper.toResponse(ticketRepository.save(ticket));

        log.info("Ticket Create Success with id {}", ticketResponse.getId());

        return ticketResponse;

    }


    @Override
    @Transactional
    public TicketResponse update(TicketRequest ticketRequest, UUID uuid) {

        Ticket ticketToUpdate = ticketRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", uuid.toString()));

        Fly fly = flyRepository.findById(ticketRequest.getFlyId())
                .orElseThrow(() -> new ResourceNotFoundException("Fly", ticketRequest.getFlyId()));

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().multiply(BigDecimal.valueOf(0.25)));
        ticketToUpdate.setPurchaseDate(LocalDate.now());
        ticketToUpdate.setArrivalDate(LocalDateTime.now());
        ticketToUpdate.setDepartureDate(LocalDateTime.now());

        TicketResponse ticketResponse = ticketMapper.toResponse(ticketRepository.save(ticketToUpdate));

        log.info("Ticket Create Success with id {}", ticketResponse.getId());

        return ticketResponse;
    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        Ticket ticketToDelete = ticketRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket",  uuid.toString()));
        ticketRepository.delete(ticketToDelete);
    }

}
