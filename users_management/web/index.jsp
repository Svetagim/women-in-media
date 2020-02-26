<%--
  Created by IntelliJ IDEA.
  User: Shoham and Sveta
  Date: 2/24/2020
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import ="com.roditigimpelson.usersmanagement.models.User"%>
<html>
  <head>
    <title>Index</title>
  </head>
  <body>
  <%
    Cookie cookies[] = request.getCookies();
    if (cookies.length != 0){
      boolean hasCookie = false;
      for (Cookie cookie : cookies){
        if (cookie.getName().equals("username")) {
          hasCookie = true;
        }
      }
      if (!hasCookie){
        response.sendRedirect("LoginPage.jsp");
        return;
      }
//      response.sendRedirect("UserLogged.jsp");
//      return;
    }
    response.sendRedirect("LoginPage.jsp");
  %>
  </body>
</html>
