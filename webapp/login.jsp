<%--
  Created by IntelliJ IDEA.
  User: pavle
  Date: 06.05.2022
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<form action="login" method="post">

    <label for="login">Login: </label> <input name="login">
    <br>
    <label for="password">Password: </label> <input name="password">
    <br>
    <input type="submit" value="submit">
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

