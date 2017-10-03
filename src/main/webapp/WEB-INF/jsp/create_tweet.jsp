<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/2/2017
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>

<form action='tweet' method="POST">
    User name: <input type="text" name="userName">
    <br/>
    Tweet text: <input type="text" name="tweetText">
    <input type="submit" value="Create">
</form>
</body>
</html>
