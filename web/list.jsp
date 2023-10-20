
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Student"%>
<%@page import="model.Major"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List</title>
    </head>
    <body>
        <table border="1px">
            <thead>
            <th>RollCode</th>
            <th>FullName</th>
            <th>DoB</th>
            <th>Gender</th>
            <th>Major</th>
            <th></th>
        </thead>
        <tbody>
            <%
                List<Student> students = (List<Student>)request.getAttribute("students");
                for (Student student : students) {
            %>
            <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getDob()%></td>
                <td><%=student.getGender()%></td>
                <td><%=student.getMajor().getMajor()%></td>
                <td>
                    <form action="detail" method="post">
                        <input type="hidden" name="id" value="<%=student.getId()%>">
                        <input type="submit" value="detail">
                    </form>        
                </td>
            </tr>
            <%
                }
            %>
        </tbody>    
        <tfoot>
            <tr>
                <td colspan="6">
                    <form action="insert">
                        <input type="submit" value="insert">
                    </form>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <form action="sort.jsp">
                        <input type="submit" value="sort">
                    </form>
                </td>
            </tr>
        </tfoot>
    </table>
</body>
</html>
