<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-12-02
  Time: 오후 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    if(id.equals("cookie") && pw.equals("cometrue")) {
        Cookie cookie_id = new Cookie("id", id);
        Cookie cookie_pw = new Cookie("pw", pw);
        response.addCookie(cookie_id);
        response.addCookie(cookie_pw);
        out.println("로그인에 성공하였습니다.");
        out.println("쿠키 생성을 성공하였습니다.");
        out.println("<a href='cookie-form.jsp'>자동 로그인 </a>");
    }
    else {
        out.println("쿠키 생성을 실패하였습니다.");
    }
%>
</body>
</html>
