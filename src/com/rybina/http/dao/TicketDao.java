package com.rybina.http.dao;

import com.rybina.http.entity.Ticket;
import com.rybina.http.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();
    private final String FIND_ALL_SQL = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket""";
    private final String FIND_ALL_BY_ID_SQL = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket where id = ?""";
    private final String FIND_ALL_BY_FLIGHT_ID_SQL = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket where flight_id = ?""";

    private TicketDao() {
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Ticket> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)
        ) {
            ArrayList<Ticket> tickets = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ID_SQL)
        ) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildTicket(resultSet));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> findAllByFlightId(Long flightId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID_SQL)
        ) {
            preparedStatement.setObject(1, flightId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }
}
