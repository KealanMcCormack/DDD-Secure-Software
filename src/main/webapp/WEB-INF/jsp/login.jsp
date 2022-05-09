<%@ page language="java" contentType="text/html; charset=UTF-8;"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center;">
    <h1 style="border-bottom: 5px solid lightgreen;">Login</h1>
    <h2>
        <a href="/">Back</a>
    </h2>
</div>
<div style="margin: 0 auto; display: table;">
    <form action="/loginCheck" method="post">
        <table style="border: solid;">
            <caption>
                <h2>
                    Please fill in the form below
                    <%
                        if(!(request.getSession().getAttribute("login")==null) && request.getSession().getAttribute("login").equals("false")){
                    %>
                    <p>I'm sorry, the login information supplied was incorrect.<p>
                    <p>Please Try again</p>
                    <%
                        }
                        if(!(request.getSession().getAttribute("login")==null) && request.getSession().getAttribute("login").equals("true")){
                    %>
                    <p>You are Already logged in<p>
                    <%
                        }
                    %>
                </h2>
            </caption>
            <tr>
                <th>Username: </th>
                <td>
                    <label>
                        <input type="text" name="username" size="45" required/>
                    </label>
                </td>
            </tr>

            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45" required/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login" />
                    &nbsp;&nbsp;
                    <input type="reset" value="Reset" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>