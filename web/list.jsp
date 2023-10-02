
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Human"%>
<%@page import="model.HumanType"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List</title>
    </head>
    <body>
        <table border="1px">
            <thead>
            <th>Name</th>
            <th>DOB</th>
            <th>Gender</th>
            <th>Type</th>
            <th></th>
        </thead>
        <tbody>
            <%
                List<Human> humans = (List<Human>)request.getAttribute("humans");
                for (Human human : humans) {
            %>
            <tr>
                <td><%=human.getName()%></td>
                <td><%=human.getDob()%></td>
                <td><%=human.getGender()%></td>
                <td><%=human.getType().getName()%></td>
                <td>
                    <form action="edit">
                        <input type="hidden" name="id" value="<%=human.getId()%>">
                        <input type="submit" value="edit">
                    </form>
                    <form action="delete">
                        <input type="hidden" name="id" value="<%=human.getId()%>">
                         <input type="submit" value="delete">
                    </form>           
                </td>
            </tr>
            <%
                }
            %>
        </tbody>    
        <tfoot>
            <tr>
                <td colspan="5">
                    <form action="insert">
                        <input type="submit" value="insert">
                    </form>
                </td>
            </tr>
        </tfoot>
    </table>
</body>
</html>
