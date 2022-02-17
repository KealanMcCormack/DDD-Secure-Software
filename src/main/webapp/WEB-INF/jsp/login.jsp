<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vaccine Registration</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Vaccine Registration</h1>
    <h2>

    </h2>
</div>
<div style="margin: 0 auto; display: table;">
    <form action="validatelogin.jsp" method="post">
        <table style="border: solid;">
            <caption>
                <h2>
                    Please fill in the form below
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
                    <input type="text" name="password" size="45" required/>
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