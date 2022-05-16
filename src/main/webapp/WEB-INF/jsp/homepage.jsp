<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vaccine Homepage</title>
</head>
<body>
<center>
    <h1 style="border-bottom: 5px solid lightgreen;">Welcome to the HSE Vaccine Website</h1>
    <% if(request.getSession().getAttribute("username") != null){
        String username = request.getSession().getAttribute("username").toString();
    %>
    <p>Welcome Back - <%=username%> - </p>
    <% } %>

    <h2>
        <a href="/login">Login</a>

        <a href="/admin_login">HSE Login</a>

        <a href="/logout">Logout</a>

    </h2>
</center>
<div align="center">
    <h2>
        <%
            if(request.getSession().getAttribute("NewlyRegistered") == null && request.getSession().getAttribute("login") == null){
        %>
        <a href="/account_register">Register to Account</a>
        <%
            }
        %>

        <%
            if(request.getSession().getAttribute("login") != null){
        %>
        <a href="/activity">View Last Activity</a>
        <%
            }
        %>

        <a href="/forum">Forum</a>

        <a href="/stats">Vaccination Statistics</a>
    </h2>
</div>

<div align="center">
<h2>
    <%
        if(request.getSession().getAttribute("login") != null && request.getSession().getAttribute("vaccinated") == null && !((boolean) request.getSession().getAttribute("isBooked"))){
    %>
    <a href="/booking">Book Vaccine</a>
    <%
        }
    %>
</h2>

</div>
</body>
</html>