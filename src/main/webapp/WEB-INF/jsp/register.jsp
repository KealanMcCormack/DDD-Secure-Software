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
    <form action="save" method="post">
        <table style="border: solid;">
            <caption>
                <h2>
                    Please fill in the form below
                </h2>
            </caption>
            <input type="hidden" name="id" value="<c:out value='${book.id}' />"  />
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="book_name" size="45"
                           value="<c:out value='${book.book_name}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>ISBN: </th>
                <td>
                    <input type="text" name="isbn" size="45"
                           value="<c:out value='${book.isbn}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Authors: </th>
                <c:forEach var="author" items="${authors}">
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${author.author_first_name} ${author.author_last_name}'/>" readonly
                        />
                    </td>

                </c:forEach>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>