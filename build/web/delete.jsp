<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete</title>
    </head>
    <body>
        <%
            String id = (String)request.getAttribute("id");
        %>
        <h1>Are you sure to delete this person's information?</h1>
        <form action="delete" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="isDelete" value="1">
            <input type="submit" value="Yes" style="padding: 5px 20px">

        </form>
        <form action="delete" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="isDelete" value="0">
            <input type="submit" value="No" style="padding: 5px 20px">
        </form>
    </body>
</html>
