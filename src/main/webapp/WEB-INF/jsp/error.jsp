<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>ERROR</title>
    </head>
    <body>
        <p>You have reached the error page</p>
        <c:if test="${not empty message}">
            <p>
                <c:out value="message = ${message}"/>
            </p>
        </c:if>
        <h2>
            <a href="/">Back to Homepage</a>
        </h2>
    </body>
</html>