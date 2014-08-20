<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Home Page</title>
    <script src="/static/js/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/main.css">
    <script>
        $(document).ready(
                function (){
                    $('#captcha').click(
                            function (){
                                $(this).attr('src', 'captcha.jpg?' + Math.floor(Math.random() * 100));});
                });
    </script>
    <style type="text/css">
        html,body{margin:10px; padding: 10px;}
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
        <sf:form method="POST" action="/" modelAttribute="loginCommand">
            <fieldset>
                <table cellspacing="0">
                    <tr>
                        <th><label for="username">Username</label></th>
                        <td><sf:input path="username" id="username" size="20"/>
                            <sf:errors path="username" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <th><label for="password">Password</label></th>
                        <td><sf:password path="password" id="password" size="20"/>
                            <sf:errors path="password" cssClass="error" />
                        </td>
                    </tr>

                    <tr>
                        <th></th>
                        <td><input name="commit" type="submit" value="Login"></td>
                    </tr>
                </table>
            </fieldset>
        </sf:form>

        <div>
            <p>Haven't an account?</p>
            <a href="register.html">register</a>
        </div>
    </div>
</div>

<p>footer</p>
</body>
</html>
