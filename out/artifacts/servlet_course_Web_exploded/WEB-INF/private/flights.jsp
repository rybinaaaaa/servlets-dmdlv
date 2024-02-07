<%--
  Created by IntelliJ IDEA.
  User: rybina
  Date: 07.02.2024
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--динамическое подключение--%>
<%@ include file="header.jsp"%>
<ul>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li>
<%--       contextPath - это тот префикс, который мы задаем в настройках tomCat, по умолчанию он у нас Root     --%>
            <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a> </li>
    </c:forEach>
</ul>
<%--статическое подключаение - после компиляции jsp --%>
<jsp:include page="footer.jsp" />
</body>
</html>
