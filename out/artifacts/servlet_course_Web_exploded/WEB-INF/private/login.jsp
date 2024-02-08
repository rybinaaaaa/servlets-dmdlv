<%--
  Created by IntelliJ IDEA.
  User: rybina
  Date: 08.02.2024
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">
        Email:
    </label>
    <input type="text" name="email" id="email" value="${param.email}" required>
    <label for="password">
        Password:
    </label>
    <input type="password" name="password" id="password" required>

    <button type="submit">
        Login
    </button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">
            Register
        </button>
    </a>
</form>
<c:if test="${param.error != null}">
    <div style="color: red">
        <span>Email or password is incorrect</span>
    </div>
</c:if>
</body>
</html>
