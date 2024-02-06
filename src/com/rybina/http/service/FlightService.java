package com.rybina.http.service;

import com.rybina.http.dao.FlightDao;
import com.rybina.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.*;

public class FlightService {

    private final FlightDao flightDao = FlightDao.getInstance();

    private final static FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream().map(flight -> new FlightDto(flight.getId(),
                """
                %s - %s - %s
                """.formatted(flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getFlightStatus()))).collect(toList());
    }
}
