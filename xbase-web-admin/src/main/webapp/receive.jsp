<%--
  Created by IntelliJ IDEA.
  User: Josue Cubero
  Date: 14/06/2017
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receive</title>
</head>
<body>

<%
    String search = request.getParameter("send");
    System.out.println(search);
    Cookie testCookie = new Cookie("xBase.recentSearch",search);
    testCookie.setMaxAge(31*24*60*60);
    response.addCookie(testCookie);
%>

<a href="MainPage.jsp">Go to main page</a>

</body>
</html>
