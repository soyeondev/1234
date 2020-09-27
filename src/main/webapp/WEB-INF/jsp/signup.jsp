<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
    <h1>회원 가입</h1>
    <hr>
     <form action="${contextPath}/user/signup" method="post"> 
     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    email(ID): <input type="text" name="username" placeholder="아이디"><br>
	   	비밀번호: <input type="password" name="password" placeholder="비밀번호"><br>
	   	사용자이름: <input type="text" name="name" placeholder="사용자 이름"><br>
	   	휴대전화: <input type="text" name="phone" placeholder="휴대전화"><br>
	   	부서: <input type="text" name="department" placeholder="부서"><br>
	   	내선번호: <input type="text" name="extNumber" placeholder="내선번호"><br>
   	  	<button type="submit">가입하기</button>
    </form>
</body>
</html>