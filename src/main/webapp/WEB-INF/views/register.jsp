<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="register.do" method="post">
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
</body>
</html>
