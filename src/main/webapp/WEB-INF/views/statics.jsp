<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Statics</title>

    <style>
        #tusers {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            width: 100%;
            border-collapse: collapse;
        }

        #tusers td, #tusers th {
            font-size: 1em;
            border: 1px solid #98bf21;
            padding: 3px 7px 2px 7px;
        }

        #tusers th {
            font-size: 1.1em;
            text-align: left;
            padding-top: 5px;
            padding-bottom: 4px;
            background-color: #A7C942;
            color: #ffffff;
        }

        #tusers tr.alt td {
            color: #000000;
            background-color: #EAF2D3;
        }

        #dtable {
            width: 80%
        }
    </style>
</head>
<body>

<div id="dtable">
<c:if test="${!empty users}">
    <table id="tusers">
        <tr>
            <td>User ID</td>
            <td>User Name</td>
            <td>Last IP</td>
            <td>Last Visit</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.lastIp}</td>
                <td>${user.lastVisit}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
