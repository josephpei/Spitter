<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Success</title>
    <style type="text/css">
        html,body{margin:10px; padding: 10px;}
        .left,.right,.center { height: 600px; }

        .left {float:left; width: 200px; background-color: #ccc;}
        .right {float:right; width: 200px; background-color: #ccc;}
        .center { margin: 0 210px; background-color: #666;}

        ul.navbar {
            list-style-type: none;
            padding: 0;
            margin: 0 }
        ul.navbar li {
            background: white;
            margin: 0.5em 0;
            padding: 0.3em;
            border-left: 1em solid black }
        ul.navbar a {
            text-decoration: none }
        a:link {
            color: blue }
        a:visited {
            color: purple }

        ul.mblog li {
            background: #ccc;
            margin-bottom: 0.5em;
            margin-right: 1em;
        }

    </style>
</head>

<body>
    <h1>Welcome, <c:out value="${sessionScope.user.getUserName()}" /> </h1>
    <div class="left">左</div>
    <div class="right">
        <span><c:out value="${sessionScope.user.getUserName()}" /></span>
        <img src="../static/img/default.jpg" />
        <!-- Site navigation menu -->
        <ul class="navbar">
            <li><a href="index.html">Home page</a>
            <li><a href="musings.html">Musings</a>
            <li><a href="town.html">My town</a>
            <li><a href="/user_home/logout">Logout</a>
        </ul>
    </div>
    <div class="center">
        <div>
            <fieldset>
            <sf:form method="post" modelAttribute="spittle">
                <sf:textarea path="text" rows="5" cols="100" />
                <br />
                <input name="commit" type="submit" value="Push">
            </sf:form>
            </fieldset>
        </div>
        <div>
            <ul class="mblog">
            <c:forEach var="spittle" items="${spittles}">
            <li>
                ${spittle.user.userName}, ${spittle.pushTime}, ${spittle.text}
            </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
