<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vaccine Homepage</title>
</head>
<body>
<center>
    <h1>Vaccine Booking</h1>
    <% if(request.getSession().getAttribute("username") != null){
        String username = request.getSession().getAttribute("username").toString();
    %>
    <p>Welcome Back - <%=username%> - </p>
    <% } %>

    <h2>
        <a href="/login">Login</a>

        <a href="/logout">Logout</a>

    </h2>
</center>
<div align="center">

    <a href="/vaccine_register">Register to Vaccine</a>
    <!-- FORUM HERE<a href="">Forum</a>-->

</div>
</body>
</html>