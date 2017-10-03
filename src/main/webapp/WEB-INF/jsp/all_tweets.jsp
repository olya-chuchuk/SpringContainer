<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<table>
    <thead>
    <tr>
        <th>№</th>
        <th>User</th>
        <th>Message</th>
    </tr>
    </thead>
    <c:forEach items="${tweetList}" var="tweet" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>
                <input type="text" name="" value="${tweet.getUser().getName()}">
            </td>
            <td>
                <input type="text" name="textField" value="${tweet.getText()}">
            </td>
            <td>
                <a href='<c:url value="all_tweets?id=${tweet.getId()}&text=${textField}"/>'>
                    <input type="submit" value="Edit"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<c:url value="start"/>">Start page</a>
<br/>
<a href="<c:url value="create_tweet"/>">Create new tweet</a>

</body>
</html>
