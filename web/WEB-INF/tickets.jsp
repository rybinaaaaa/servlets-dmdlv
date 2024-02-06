<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.rybina.http.service.TicketService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rybina.http.dao.TicketDao" %>
<%@ page import="com.rybina.http.dto.TicketDto" %><%--
  Created by IntelliJ IDEA.
  User: rybina
  Date: 06.02.2024
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты</h1>
<%--Использование скрипеты--%>
<ul>
<%
    List<TicketDto> tickets = TicketService.getInstance().findAllByFlightId(Long.valueOf(request.getParameter("flightId")));
    for (TicketDto ticket : tickets) {
        out.write(String.format("<li> %s </li>", ticket.getSeatNo()));
    }
%>
</ul>
</body>
</html>
