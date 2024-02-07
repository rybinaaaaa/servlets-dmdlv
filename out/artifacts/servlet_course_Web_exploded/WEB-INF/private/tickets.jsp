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
<%@ taglib prefix="my_name" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты</h1>
<ul>
<my_name:forEach var="ticket" items="${requestScope.tickets}">
    <li>
        ${fn:toLowerCase(ticket.seatNo)}
    </li>
</my_name:forEach>
</ul>
</body>
</html>
