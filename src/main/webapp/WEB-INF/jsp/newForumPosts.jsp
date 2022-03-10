<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vaccine Forum</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Create a new post</h1>
    <h2>
        <a href="/forum">Back to Forum</a>
    </h2>
</div>
<div align="center">
    <form action="/addForumPost" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Forum Post</h2>
            </caption>
            <tr>
                <th>Title: </th>
                    <td>
                        <input type="text" name="title" size="45" required/>
                    </td>
                </tr>

                <tr>
                    <th>Content: </th>
                    <td>
                        <input type="text" name="content" size="600" required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
