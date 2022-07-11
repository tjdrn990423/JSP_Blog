<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-12-01
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="jspservlet.ch06.domain.*" %>
<html>
<head>
    <title>Address Table</title>
</head>
<body>
<h1>Found Name</h1>
<%--
  Blogger blogger = (Blogger) request.getAttribute("blogger");
  out.println("Name :" + blogger.getEmail());

--%>
Name : ${blogger.name}<br/>
Pw : ${requestScope.blogger.pw}<br/>
Email : ${requestScope.blogger.email}<br/>
</body>
</html>
