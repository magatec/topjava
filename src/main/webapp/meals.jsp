
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${(meal.excess ? 'red' : 'green')}">
            <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDataTime" type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDataTime}"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="index.html">Update</a></td>
            <td><a href="index.html">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
