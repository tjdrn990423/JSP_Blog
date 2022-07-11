<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-12-01
  Time: 오후 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url value="https://stackoverflow.com/search" var="searchUrl">
    <c:param name="q" value="c url" />
    <c:param name="s" value="7b8cc437-5ca5-419e-914a-58d1d60538a8" />
</c:url>
<c:out value="${searchUrl }" /><br/>
<%--
<c:import url="https://stackoverflow.com/search">
    <c:param name="q" value="jstl import" />
    <c:param name="s" value="7b8cc437-5ca5-419e-914a-58d1d60538a8" />
</c:import>
--%>

<%--
<%
    String str1 = request.getParameter("n1");
    String str2 = request.getParameter("n2");
    int num1 = Integer.parseInt(str1);
    int num2 = Integer.parseInt(str2);
%>
<c:catch var="exceptionName">
    <% int result = num1 / num2; %>
    나눗셈 결과는 <%= result %>
</c:catch>
<c:if test="${exceptionName != null}">
    에러메세지 :${exceptionName.message}
</c:if>
<br/>
--%>
구분자로 구분하기 전 토큰의 집합 :
<c:set var="fileExtensions" value="java,jsp,class,zip,hwp,doc" />
${fileExtensions }<br/>
쉼표(,)를 구분자로 구분한 상태 : <br/>
<c:forTokens items="${fileExtensions }" delims="," var="extension" varStatus="status">
    ${status.count} - ${extension }<br/>
</c:forTokens>


<h3>Set the test result to a variable</h3>
<c:if test="${1==1}" var="theTruth" scope="page"/>
The result of testing for (1==1) is: ${theTruth}
<h3>Conditionally execute the body</h3>
<c:if test="${2>0}">
    <p>It's true that (2>0)! Working.</p>
</c:if>
<c:if test="${0>2}">
    <p>It's not true that (0>2)! Failed.</p>
</c:if>

<c:forEach var="index" begin="0" end="4">
    # ${index}:
    <c:choose>
        <c:when test="${index == 1}">
            One!<br/>
        </c:when>
        <c:when test="${index == 4}">
            Four!<br/>
        </c:when>
        <c:when test="${index == 3}">
            Three!<br/>
        </c:when>
        <c:otherwise>
            Huh?<br/>
        </c:otherwise>
    </c:choose>
</c:forEach>


<%
    int sum = 0;
    for(int i = 1; i <= 100; i++)
        sum = sum + i;
    out.println("<h1> scripting : " + sum + "</h1>");
%>

<c:set var="sum" value="0"/>
<c:forEach var="i" begin="1" end="100">
    <c:set var="sum" value="${sum+i}"/>
</c:forEach>
<h1>합은 : ${sum} </h1>
</body>
</html>
