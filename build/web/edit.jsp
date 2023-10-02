
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.HumanType"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>
        <form action="edit" method="post">
            <!--id of the human-->
            <%
               String id = (String)request.getAttribute("id");
            %>
            <input type="hidden" name="id" value="<%=id%>">
            Name: <input type="text" name="name">
            <br>
            DOB: <input type="date" name="dob">
            <br>
            Gender:
            <label>
                <input type="radio" name="gender" value="Male">Male
            </label>
            <label>
                <input type="radio" name="gender" value="Female">Female
            </label>  
            <br>
            Type:
            <%
               List<HumanType> types = (List<HumanType>)request.getAttribute("types");
            %>
            <select name="type">
                <% for (HumanType type : types) { %>
                <option value="<%=type.getName()%>"><%=type.getName()%></option>
                <% } %>
            </select>
            <br>
            <input type="submit" value="edit">
        </form>
    </body>
</html>
