<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인</title>
</head>
<body>
    <h1>메인 페이지</h1>
    <hr>
   	<a href="${contextPath}/user/signup">회원가입</a>
   	<a href="${contextPath}/user/login">로그인</a>
    <%
    	String id = (String)session.getAttribute("name");
    	String time = (String)session.getAttribute("time");
    %>

	<%= id %>님 <%=time %>에 로그인 하셨습니다.
</body>
</html>