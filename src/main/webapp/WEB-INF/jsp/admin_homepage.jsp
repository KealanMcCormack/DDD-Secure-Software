<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Homepage</title>
</head>
<body>
<center>
    <h1>Admin Homepage</h1>
    <% if(request.getSession().getAttribute("username") != null){
        String username = request.getSession().getAttribute("username").toString();
    %>
    <p>Welcome Back - <%=username%> - </p>
    <% } %>

</center>
<div align="center">
<h2>
    <a href="/registerNewUser">Register new HSE User</a>

    <!-- VIEW USER DATA<a href=">View User Data</a>-->

    <a href="/forum">Forum</a>
</h2>



</div>
</body>
</html>