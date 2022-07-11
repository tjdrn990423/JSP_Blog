<%--
  Created by IntelliJ IDEA.
  User: tjdrn
  Date: 2021-12-01
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<c:import var="xmlDoc" url="x-blogger.xml" charEncoding="utf-8"/>
<x:parse var="doc" doc="${xmlDoc}" />
<table border=1 width="500px">
    <tr><th>xMember.xml 파일 읽기</th></tr>
    <tr><td valign="top"><pre><c:out value="${xmlDoc}"/></pre></td></tr>
    <tr><th>x:forEach로 선택해서 읽기</th></tr>
    <x:forEach var="item" select="$doc//blogger" begin="0" end="2" step="1">
        <tr><td>
            <x:out select="$item/name" /><br/>
            <x:out select="$item/phone" /><br/>
            <x:out select="$item/department" /><br/>
        </td></tr>
    </x:forEach>
</table>

