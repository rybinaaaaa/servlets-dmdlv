<%--
  Created by IntelliJ IDEA.
  User: rybina
  Date: 06.02.2024
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
    <div>
        <span>Content</span>
        <p>Size ${requestScope.flights.size()}</p>

<%--        Так лучше не делать--%>
<%--        <p>Id ${requestScope.flights.get(0).id}</p>--%>
        <p>Id ${requestScope.flights[1].id}</p>
        <p>Map ${sessionScope.flightsMap[1]}</p>
        <p>JSessionId id: ${cookie["JSESSIONID"]}</p>
    </div>
<%@include file="footer.jsp"%>
</body>
</html>
