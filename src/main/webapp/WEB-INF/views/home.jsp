<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Home Page</title>
    <script>
        $(document).ready(
                function (){
                    $('#captcha').click(
                            function (){
                                $(this).attr('src', 'captcha.jpg?' + Math.floor(Math.random() * 100));
                            }
                    );
                }
        );
    </script>
    <style type="text/css">
        html,body{margin:10; padding: 10;}
        .m-box .left,.m-box .right { height: 500px; }

        .m-box .left{float:left; width:800px; background-color:#ccc; }
        .m-box .right{margin-left:168px;  background-color:#666;}
    </style>
</head>
<body>
<h1>Home Page</h1>

<div class="m-box">
    <div class="left">左</div>

    <div class="right">
        <p>Login</p>
        <form action="login.do" method="post">
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
                    <td><input type="text" name="captcha"></td>
                    <td><img id="captcha" src="captcha.jpg" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
        <br />
        <p>Haven't an account?</p>
        <a href="register.html">register</a>
    </div>
</div>

<p>footer</p>
</body>
</html>
