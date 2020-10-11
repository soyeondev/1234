<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>
$(function(){
	$.ajax({
		type: "get",
		url: "${contextPath}/member/ajax_list",
		success: function(data){
			console.log(data);
			for(var i = 0; i < data.memberDtos.length; i++){
				console.log(data.memberDtos)
				$("thead").after("<tr>"+
				"<td>"+data.memberDtos[i].memberKey+"</td>"+
				"<td>"+data.memberDtos[i].username+"</td>"+
				"<td>"+data.memberDtos[i].name+"</td>"+
				"<td>"+data.memberDtos[i].phone+"</td>"+
				"<td>"+data.memberDtos[i].department+"</td>"+
				"<td>"+data.memberDtos[i].extNumber+"</td>"+
				"<td>"+data.memberDtos[i].createAt+"</td>"+
				"</tr>");				
			}
		}
		,error: function(error){
			console.log(error);
		}
	});
	
	$("table tr").click(function(){
		var memberKey = $(this);
		var td = memberKey.children();
		
		var memberArr = new Array();
		
		td.each(function(i){
			memberArr.push(td.eq(i).text());				
		});

		console.log(memberArr);

		$("#username").val(memberArr[1]);
		$("#name").val(memberArr[2]);
		$("#phone").val(memberArr[3]);
		$("#department").val(memberArr[4]);
		$("#extNumber").val(memberArr[5]);
		
	});
	
	$("#btnUpdate").click(function(){
		var data = $("#memberUpdate").serialize();
		console.log(data);
		
		$.ajax({
			type: "post",
			url: "${contextPath}/member/update",
			data: data,
			success: function(){
				console.log("ajax 통신성공")
			},
			error: function(error){
				console.log(error);
			}
		});
	});
});
</script>
<body>
	<table>
			<thead>
				<th>No</th>
				<th>email(ID)</th>
				<th>사용자이름</th>
				<th>휴대전화</th>
				<th>부서</th>
				<th>내선번호</th>
				<th>등록일자</th>
			</thead>
	</table>
	<div id="memberDetail" class="member_detail">
		<form method="post" id="memberUpdate"> 
	     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    email(ID): <input type="text" name="username" id="username" value=""><br>
		   	<!-- 비밀번호: <input type="password" name="password" id="password" ><br> -->
		   	사용자이름: <input type="text" name="name" id="name" ><br>
		   	휴대전화: <input type="text" name="phone" id="phone" ><br>
		   	부서: <input type="text" name="department" id="department" ><br>
		   	내선번호: <input type="text" name="extNumber" id="extNumber" ><br>
    	</form>
	   	  	<button id="btnUpdate">수정하기</button>
	</div>
</body>
</html>