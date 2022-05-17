<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Security-Policy" content="default-src 'self'">
    <title>Edit User Data</title>
</head>
<body>
    <h2>
        All Appointments
        <a href="/admin_homepage">Back</a>
    </h2>
    <table>
        <tr>
            <th>Centre Name</th>
            <th>Time</th>
            <th>Date</th>
            <th>Username</th>
        </tr>
        <c:forEach items="${users}" var="usr">
            <tr>
                <td>${usr.name}</td>
                <td>${usr.surname}</td>
                <td>${usr.email}</td>
                <td>vaccination status: ${usr.vaccinationStage}</td>
                <td>
                    Change vaccination status and specify type :
                    <form action="<c:url value= "/viewUserDataChangeVacc/${usr.PPS}"/>" method="post">
                        <input type="number" name="newVaccStatus">
                        <input type="text" name="vaccineType">
                        <input type="submit" value="Change Vaccination Status">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>