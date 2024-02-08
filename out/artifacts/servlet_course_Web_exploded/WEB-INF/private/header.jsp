<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <div id="logout">
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </c:if>
    </div id="locale">
    <form action="${pageContext.request.contextPath}/locale" method="post">
        <button type="submit" name="lang" value="en_UA">EN_UA</button>
        <button type="submit" name="lang" value="en_UK">EN_UK</button>
    </form>
</div>