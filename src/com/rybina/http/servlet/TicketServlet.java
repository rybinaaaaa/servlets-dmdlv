package com.rybina.http.servlet;

import com.rybina.http.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Купленные билеты</h1>");
            writer.write("<ul>");
            ticketService.findAllByFlightId(flightId).stream().forEach(ticketDto -> {
                writer.write("""
                        <li>
                        %s
                        </li>
                        """.formatted(ticketDto.getSeatNo()));
            });
            writer.write("</ul>");
        }
    }
}
