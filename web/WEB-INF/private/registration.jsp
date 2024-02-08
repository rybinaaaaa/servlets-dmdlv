<%--
  Created by IntelliJ IDEA.
  User: rybina
  Date: 07.02.2024
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/images/users/image.jpeg">
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="name">
        Name:
        <input type="text" name="name" id="name"/>
    </label>
    <br>
    <label for="birthday">
        Birthday:
        <input type="date" name="birthday" id="birthday"/>
    </label>
    <br>
    <label for="email">
        Email:
        <input type="email" name="email" id="email"/>
    </label>
    <br>
    <label for="image">
        Image:
        <input type="file" name="image" id="image"/>
    </label>
    <br>
    <label for="password">
        Password:
        <input type="password" name="password" id="password" required/>
    </label>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>
    <br>
    <c:forEach var="g" items="${requestScope.gender}">
        <label for="gender${g}">${g}</label><input type="radio" id="gender${g}" name="gender" value="${g}"/>
    </c:forEach>
    <br>
    <button type="submit">
        Send form
    </button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>
                    ${error.message}
                </span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
