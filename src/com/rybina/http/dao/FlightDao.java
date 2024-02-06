package com.rybina.http.dao;

import com.rybina.http.entity.Flight;
import com.rybina.http.entity.FlightStatus;
import com.rybina.http.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Integer, Flight>{
    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {
    }

    public static final String FIND_ALL = """
            SELECT * FROM flight
            """;


    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Flight> flights = new ArrayList<>();

            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }

            return flights;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public Optional<Flight> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        LocalDateTime departureDate = resultSet.getObject("departure_date", LocalDateTime.class);
        String flightNo = resultSet.getString("flight_no");
        String departureAirportCode = resultSet.getString("departure_airport_code");
        LocalDateTime arrivalDate = resultSet.getObject("arrival_date", LocalDateTime.class);
        String arrivalAirportCode = resultSet.getString("arrival_airport_code");
        Integer aircraftId = resultSet.getInt("aircraft_id");
        FlightStatus flightStatus = FlightStatus.valueOf(resultSet.getString("status"));

        return new Flight(
                id,
                departureDate,
                flightNo,
                departureAirportCode,
                arrivalDate,
                arrivalAirportCode,
                aircraftId,
                flightStatus
        );
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
