<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>로그인</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>


<h1>로그인</h1>
<form action='login.do' method='POST'>
<table border='1'>
<tr><th>회원유형</th><td>
     <input type='radio' name='userType' value='student' checked>학생
     <input type='radio' name='userType' value='teacher'>강사
     <input type='radio' name='userType' value='manager'>운영자
</td></tr>
<tr><th>이메일</th><td><input name='email' type='text' placeholder='예)hong@gmail.com' value='${cookie.email.value}'></td></tr><tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th></th><td><input name='saveEmail' type='checkbox' checked>이메일 저장</td></tr>
</table>
<button type='submit'>로그인</button>
</form>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
 </html>
