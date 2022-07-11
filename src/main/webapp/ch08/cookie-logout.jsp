<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-12-02
  Time: 오후 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //쿠키가져오기
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("id")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
//실행흐름이 서버에 있을때 서버코드로써 강제이동한다.
//특정 page로 이동하라는 정보만 준다.
                response.sendRedirect("cookie-login-check.jsp");
            }
        }
    }
%>
</body>
</html>
