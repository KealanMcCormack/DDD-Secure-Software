<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Last Activity</title>
</head>
<body>
<center>
<h1>
    <% if(request.getSession().getAttribute("username") != null){
        String username = request.getSession().getAttribute("username").toString();
    %>
    <p>View Last Activity for - <%=username%> - </p>
    <% } %>

</h1>
</center>
<table id="lastActivity">
    <tr>
        <th>Centre Name</th>
        <th>Time</th>
        <th>Date</th>
    </tr>
    <c:forEach items="${vaccineAppointments}" var="appointment">
        <tr>
            <td>${appointment.centre}</td>
            <td>${appointment.time}</td>
            <td>${appointment.date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>