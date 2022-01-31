<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create ZIP</title>
</head>
<body>
<div align="center">
    <form action="/create_zip_photos" method="POST">
        <c:forEach items="${photo_id}" var="photo">
            <div>
                <p align="center">
                    <label>
                        <input type="checkbox" name="photoId" value="${photo}">
                    </label>
                        ${photo}
                    <img src="/photo/${photo}" height="50px" alt="pic${photo}"/>
                </p>
            </div>
        </c:forEach>
        <input type="submit" value="Create zip"/>
    </form>
</div>
</body>
</html>