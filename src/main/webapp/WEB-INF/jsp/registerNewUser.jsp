<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New HSE User</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Create New HSE User</h1>
</div>
<div style="margin: 0 auto; display: table;">
    <form action="/createNewHSEUser" method="post">
        <table style="border: solid;">
            <caption>
                <h2>
                    Please fill in the form below

                    <%
                        if(request.getSession().getAttribute("already_registered") != null){
                    %>
                    <p>I'm sorry, this account already exists.<p>
                    <p>Please Try again</p>
                    <%
                        }
                    %>
                </h2>
            </caption>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="email" name="email" size="45" required/>
                </td>
            </tr>
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45" required/>
                </td>
            </tr>
            <tr>
                <th>Password of new User: </th>
                <td>
                    <input type="password" name="password" size="45" required/>
                </td>
            </tr>

            <tr>
                <td style="text-align: center;">
                    <label>User access level:</label>

                    <select name="privilege">
                        <option value="HSE">HSE Staff</option>
                        <option value="Admin">Admin</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>