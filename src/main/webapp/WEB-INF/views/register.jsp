<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Register</title>
    <script src="/resources/js/jquery-1.11.0.min.js"></script>

    <style type="text/css">
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body>
<h1>Register</h1>
<sf:form method="POST" action="register.do" modelAttribute="registerCommand">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="username">Username</label></th>
                <td><sf:input path="username" id="username" size="15" maxlength="15"/>
                    <sf:errors path="username" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th><label for="fullname">Nickname</label></th>
                <td><sf:input path="fullname" id="fullname" size="15"/>
                    <sf:errors path="fullname" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th><label for="password">Password</label></th>
                <td><sf:password path="password" id="password" size="30"/>
                    <sf:errors path="password" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th><label for="confirmation">Password</label></th>
                <td><sf:password path="confirmation" id="confirmation" size="30"/>
                    <sf:errors path="confirmation" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th><label for="email">Email</label></th>
                <td><sf:input path="email" id="email" size="30"/>
                    <sf:errors path="email" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th></th>
                <td><input name="commit" type="submit" value="I accept. Create my account."></td>
            </tr>
        </table>
    </fieldset>
</sf:form>

</body>
</html>
