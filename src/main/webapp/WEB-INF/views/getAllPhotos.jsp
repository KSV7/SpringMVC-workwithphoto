<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Photos</title>
</head>
<body>
<div align="center">
    <table border="1" width="50%" cellpadding="5">
        <c:forEach items="${photo_id}" var="photo">
            <tr>
                <th align="center">ID photo</th>
                <th align="center">Photo</th>
                <th align="center">Delete</th>
            </tr>
            <tr>
                <td align="center">${photo}</td>
                <td align="center"><img src="/photo/${photo}" height="200px" /></td>
                <td align="center"><input type="submit" value="delete" onclick="window.location='/delete/${photo}';" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
