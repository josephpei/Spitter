<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Success</title>
    <style type="text/css">
        html,body{margin:10; padding: 10;}
        .left,.right,.center { height: 600px; line-height:200px; text-align:center; }

        .left {float:left; width: 200px; background-color: #ccc;}
        .right {float:right; width: 200px; background-color: #ccc;}
        .center { margin: 0 210px; background-color: #666;}
    </style>
</head>

<body>
    <h1>Welcome, <c:out value="${name}"/>!</h1>
    <div class="left">左</div>
    <div class="right">右</div>
    <div class="center">中</div>
</body>
</html>
