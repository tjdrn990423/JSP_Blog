<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-10-20
  Time: 오후 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--requestScope 객체의 msg라는 애트리뷰트 값을 접근(get)함 -->
${requestScope.msg}
${requestScope.blog.email}
</body>
</html>
