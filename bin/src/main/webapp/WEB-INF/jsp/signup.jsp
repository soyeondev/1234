<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
    <h1>회원 가입</h1>
    <hr>
     <form action="/user/signup" method="post"> 
<!--     <form action="@{/user/signup}" method="POST"> -->
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        아이디: <input type="text" name="username" placeholder="아이디">
        비밀번호: <input type="password" name="password" placeholder="비밀번호">
        사용자 이름: <input type="text" name="name" placeholder="사용자 이름">
      	<button type="submit">가입하기</button>
    </form>
</body>
</html>