package com.rybina.http.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String passangerNo;
    private String passangerName;
    private Long flightId;
    private String seatNo;
    private BigDecimal cost;

    public Ticket(Long id, String passangerNo, String passangerName, Long flightId, String seatNo, BigDecimal cost) {
        this.id = id;
        this.passangerNo = passangerNo;
        this.passangerName = passangerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassangerNo() {
        return passangerNo;
    }

    public void setPassangerNo(String passangerNo) {
        this.passangerNo = passangerNo;
    }

    public String getPassangerName() {
        return passangerName;
    }

    public void setPassangerName(String passangerName) {
        this.passangerName = passangerName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
