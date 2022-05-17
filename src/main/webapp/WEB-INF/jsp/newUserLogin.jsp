<%@ page language="java" contentType="text/html; charset=UTF-8;"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Security-Policy" content="default-src 'self'">
    <title>New User Login</title>
</head>
<body>
<div style="text-align: center;">
    <h1 style="border-bottom: 5px solid lightgreen;">New User Login</h1>
    <h2>
        <a href="/">Back</a>
    </h2>
</div>
<div style="margin: 0 auto; display: table;">
    <form action="/addLoginDetails" method="post">
        <table style="border: solid;">
            <%
                if(!(request.getSession().getAttribute("NewlyRegistered")==null) && request.getSession().getAttribute("NewlyRegistered").equals("true")){
            %>
            <caption>
                <h2>
                    Please create your account below
                    <%
                        if(!(request.getSession().getAttribute("UsernameTaken")==null) && request.getSession().getAttribute("UsernameTaken").equals("true")){
                    %>
                    <p>I'm sorry, this username was taken<p>
                    <%
                        }
                    %>

                        <%
                        if(!(request.getSession().getAttribute("PasswordWeak")==null) && request.getSession().getAttribute("PasswordWeak").equals("true")){
                    %>
                    <p>Given password wasn't strong<p>
                        <%
                        }
                    %>

                                <%
                        if(!(request.getSession().getAttribute("usernameIncorrect")==null) && request.getSession().getAttribute("usernameIncorrect").equals("true")){
                    %>
                    <p>Username wasn't correct format<p>
                        <%
                        }
                    %>
                </h2>
            </caption>
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45" required/>
                </td>
            </tr>

            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45" required autocomplete="new-password" minlength="8">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login" />
                    &nbsp;&nbsp;
                    <input type="reset" value="Reset" />
                </td>
            </tr>
            <%
                } else{
            %>
            <p>I'm sorry, you need to register before creating an account</p>
            <%
                }
            %>
        </table>

        <p style="text-align: center">
            Password must:
        </p>

        <p style="text-align: center">
            Be at least 8 characters long
        </p>

        <p style="text-align: center">
            Contain at least 2 upper case letters
        </p>

        <p style="text-align: center">
            Contain at least 1 special character
        </p>

        <p style="text-align: center">
            Contain at least 2 numbers
        </p>
    </form>
</div>
</body>
</html>