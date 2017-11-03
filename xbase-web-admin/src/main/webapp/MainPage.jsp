<%--
  Created by IntelliJ IDEA.
  User: Josue Cubero
  Date: 14/06/2017
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String value;
    if(cookies==null){
        //set properties of last search to hidden
    } else {
        for(Cookie temporalCookie : cookies){
            value = temporalCookie.getValue();
            //aca lo que se puede hacer es que se haga un link con value
            //con la misma funcion que usa para buscar.
            //yo diria que con las ultimas 3 busquedas no la juguemos
        }
    }
%>
</body>
</html>
