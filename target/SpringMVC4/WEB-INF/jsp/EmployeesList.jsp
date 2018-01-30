<%--
  Created by IntelliJ IDEA.
  User: richa
  Date: 1/29/18
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Spring Web MVC project</title>
</head>

<body>
<h1>List of Employees</h1>
</body>

<c:if test="${not empty lists}">
<c:forEach items="${lists}" var="lists">
    ${lists}
</c:forEach>
</c:if>
