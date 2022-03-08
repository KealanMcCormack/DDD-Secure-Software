<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../static/css/booking.css">
    <title>Booking</title>
</head>
<body>
<table id="bookingTable">
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
            <c:if test="${appointment.isBooked == 'true'}">
                <td>Not available</td>
            </c:if>
            <c:if test="${appointment.isBooked == 'false'}">
                <td>
                    <input type="submit" value="Book">
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>