<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
</head>
<body>
<div style="text-align: center;">
    <h1 style="border-bottom: 5px solid lightgreen;">Forum</h1>
</div>
<center>
    <h1>
        <% if(request.getSession().getAttribute("username") != null){
            String username = request.getSession().getAttribute("username").toString();
        %>
        <p>View Forum for - <%=username%> - </p>
        <% } %>

    </h1>
    <h2>
        <%
            if(request.getSession().getAttribute("privilege") != null){
        %>
        <a href="/admin_homepage">Back</a>
        <%
            } else{
        %>
        <a href="/">Back</a>
        <%
            }
        %>
        &nbsp;&nbsp;
        <%
            if(request.getSession().getAttribute("username") != null){
        %>
        <a href="/newForumPost">Make a Post</a>
        <%
            }
        %>

        <%
            if(request.getSession().getAttribute("forum_content_error") != null){
        %>
        <p>I'm sorry, the content of the post was not allowed<p>
        <p>Please Try again</p>
        <%
            }
        %>

    </h2>
</center>
<table id="lastActivity">
    <c:forEach items="${forumposts}" var="post">
        <tr>
            <td>---------------------------COMMENT-----------------------------------</td>
        </tr>
        <tr>
            <td>Title : ${post.title}</td>
        </tr>
        <tr>
            <td>Content: ${post.content}</td>
        </tr>
        <tr>
            <td>Author of Post : ${post.authorName}</td>
        </tr>
        <tr>
            <td>----------------------COMMENT SECTION----------------------------------</td>
        </tr>
        <c:forEach items="${comments}" var="comment">
            <c:if test="${comment.forumPostId == post.id}" >
                <tr>
                    <td>Comment : ${comment.comment}</td>
                </tr>
                <tr>
                    <td>Author of Comment : ${comment.poster}</td>
                </tr>
                <tr>
                    <td>------------------</td>
                </tr>
            </c:if>
        </c:forEach>

        <%
            if(request.getSession().getAttribute("privilege") != null && request.getSession().getAttribute("privilege").equals("HSE")){
        %>

        <tr>
            <td>
                <form action="/addForumComment/${post.id}" method="post">
                <label for="comment">Add a comment:</label>
                <input type="text" id="comment" name="comment"><br><br>
                <input type="submit" value="Submit">
            </form>
            </td>
        </tr>

        <%
            }
        %>
    </c:forEach>
</table>
</body>
</html>