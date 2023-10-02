
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.HumanType"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
    </head>
    <body>
        <form action="insert" method="post">
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
               for (HumanType type : types) {
            %>
            <label>
                <input type="radio" name="type" value="<%=type.getName()%>"><%=type.getName()%>
            </label>
            <%
                }
            %>
            <br>
            <input type="submit" value="insert">
        </form>
    </body>
</html>
