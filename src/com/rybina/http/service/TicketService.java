package com.rybina.http.service;

import com.rybina.http.dao.TicketDao;
import com.rybina.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {

    private final static TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAll() {
        return ticketDao.findAll().stream().map(ticket -> new TicketDto(ticket.getId(), ticket.getFlightId(), ticket.getSeatNo())).collect(toList());
    }

    public List<TicketDto> findAllByFlightId(Long id) {
        return ticketDao.findAllByFlightId(id).stream().map(ticket -> new TicketDto(ticket.getId(), ticket.getFlightId(), ticket.getSeatNo())).collect(toList());
    }

    public TicketDto findById(Long id) {
        return ticketDao.findById(id)
                .map(ticket -> new TicketDto(ticket.getId(), ticket.getFlightId(), ticket.getSeatNo()))
                .orElse(null);
    }
}
