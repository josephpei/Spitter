<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Home Page</title>
    <script>
        $(document).ready(
                function (){
                    $('#captcha').click(
                            function (){
                                $(this).attr('src', 'captcha.jpg?' + Math.floor(Math.random() * 100));});
                });
    </script>
    <style type="text/css">
        html,body{margin:10; padding: 10;}
        .m-box .left,.m-box .right { height: 500px; }

        .m-box .left{float:left; width:800px; background-color:#ccc; }
        .m-box .right{margin-left:168px;  background-color:#666;}
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body>
<h1>Home Page</h1>

<div class="m-box">
    <div class="left">左</div>

    <div class="right">
        <p>Login</p>
        <form:form action="/" method="post" commandName='loginCommand'>
            <label for="name">Name: </label>
            <form:input path="name" id="name" />
            <br />
            <form:errors path="name" class="error" />
            <br />
            <label for="pass">Pass: </label>
            <form:password path="pass" id="pass" />
            <br />
            <form:errors path="pass" class="error" />
            <br />
            <label for="captcha" id="captcha">Captcha: </label>
            <form:input path="captcha" id="captcha" />
            <img id="captcha" src="captcha.jpg" />
            <c:if test="${!empty yzm}">
                <span class="error"><c:out value="${yzm}" /></span>
            </c:if>
            <br />
            <input type="submit" value="Submit" />
        </form:form>
        <br />
        <p>Haven't an account?</p>
        <a href="register.html">register</a>
    </div>
</div>

<p>footer</p>
</body>
</html>
