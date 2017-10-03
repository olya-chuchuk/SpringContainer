<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/3/2017
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>

    <a href="<c:url value="create_tweet"/>">Create new tweet</a>
    <br/>
    <a href="<c:url value="all_tweets"/>">Show all tweets</a>

</body>
</html>
