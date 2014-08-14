<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<hr/>
<h1>Login Page</h1>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>

<form action="login" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
