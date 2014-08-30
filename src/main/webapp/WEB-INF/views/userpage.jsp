<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Success</title>
    <script src="/resources/js/jquery-1.11.0.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function() {
            $('#newSpittle').submit(function(event) {

                var mess = $('#text').val();
                var formData = { "text" : mess};

                $.ajax({
                    url: $("#newSpittle").attr( "action"),
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    async: false,
                    data: JSON.stringify(formData),

                    success: function(spittle) {
                        var respContent = "";
                        var delLink = "/u/" + spittle.user.userName + "/delete/" + spittle.spittleId;

                        respContent += "<li>";
                        respContent += spittle.user.userName + ", ";
                        respContent += spittle.pushTime + ", " ;
                        respContent += spittle.text;
                        respContent += '<a href="' + delLink + '">Delete</a>';
                        respContent += "</li>";

                        $('#mblog').prepend(respContent);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        var respBody = $.parseJSON(jqXHR.responseText);
                        alert("Error: " + respBody.message);
                    }
                });

                event.preventDefault();
            });

            var deleteLink = $("a:contains('Delete')");

            $(deleteLink).click(function(event) {

                $.ajax({
                    url: $(event.target).attr("href"),
                    type: "DELETE",

                    success: function(id) {
                        alert("delete" + id);
                        var rowToDelete = $(event.target).closest("li");

                        rowToDelete.remove();
                    }
                });

                event.preventDefault();
            });

        });
    </script>

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
        <img src="../resources/img/default.jpg" />
        <!-- Site navigation menu -->
        <ul class="navbar">
            <li><a href="index.html">Home page</a>
            <li><a href="musings.html">Musings</a>
            <li><a href="town.html">My town</a>
            <li><a href="/u/${sessionScope.user.getUserName()}/logout">Logout</a>
        </ul>
    </div>
    <div class="center">
        <div>
            <fieldset>
            <sf:form id="newSpittle" method="post" modelAttribute="spittle">
                <sf:textarea id="text" path="text" rows="5" cols="100" />
                <br />
                <input name="commit" type="submit" value="Push">
            </sf:form>
            </fieldset>
        </div>
        <div id="newWeibo"></div>

        <div>
            <ul id="mblog" class="mblog">
            <c:forEach var="spittle" items="${spittles}">
            <li>
                ${spittle.user.userName}, ${spittle.pushTime}, ${spittle.text}
                <a href="/u/${sessionScope.user.getUserName()}/delete/${spittle.spittleId}">Delete</a>
            </li>
            </c:forEach>
            </ul>
        </div>
    </div>

</body>
</html>
