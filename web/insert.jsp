
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
    </head>
    <body>
        <form action="insert" method="post">
            Rollcode: <input type="text" name="id">
            <br>
            Name: <input type="text" name="name">
            <br>
            DOB: <input type="date" name="dob">
            <br>
            Gender:
            <label>
                <input type="radio" name="gender" value="Nam">Nam
            </label>
            <label>
                <input type="radio" name="gender" value="Nu">Nu
            </label>  
            <br>
            Major:
            <select name="major">
                <%
                   List<Major> majors = (List<Major>)request.getAttribute("majors");
                   for (Major major : majors) {
                %>
                <option type="radio" value="<%=major.getMajor()%>"><%=major.getMajor()%></option>    
                <%
                    }
                %>
            </select>
            <br>
            <input type="submit" value="insert">
        </form>
    </body>
</html>
