
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sort</title>
    </head>
    <body>
        <form action="sort" method="post">
            Sort by:
            <select name="sortby">
                <option type="radio" value="gender">Gender</option>    
                <option type="radio" value="major">Major</option> 
                <option type="radio" value="id">RollCode</option> 
            </select>
            <input type="submit" value="sort">
        </form>
    </body>
</html>
