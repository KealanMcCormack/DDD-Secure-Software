<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/booking.css">
    <title>Booking</title>
</head>
<body>
<table class="bookingTable">
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
                <td class="bookingSlot">
                    <form action="<c:url value= "/bookRequest/${appointment.id}"/>" method="post">
                        <input type="submit" value="Book">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>