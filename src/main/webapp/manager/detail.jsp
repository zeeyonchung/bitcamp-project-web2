<%@page import="java.util.List"%>
<%@page import="bitcamp.java89.ems2.domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>매니저관리-상세정보</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>매니저 정보</h1>
<form action='update.do' method='POST' enctype='multipart/form-data'>
<table border='1'>

<tr><th>이메일</th><td><input name='email' type='text' value='${manager.email}'></td></tr>
<tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th>이름</th><td><input name='name' type='text' value='${manager.name}'></td></tr>
<tr><th>전화</th><td><input name='tel' type='text' value='${manager.tel}'></td></tr>
<tr><th>직급</th><td><input name='position' type='text' value='${manager.tel}'></td></tr>
<tr><th>팩스</th><td><input name='fax' type='text' value='${manager.tel}'></td></tr>
<tr><th>사진</th><td><img src='../upload/${manager.photoPath}' height='80'><input name='photoPath' type='file'></td></tr>
</table>


<button type='submit'>변경</button>
<a href='delete.do?memberNo=${manager.memberNo}'>삭제</a>
<input name='memberNo' type='hidden' value='${manager.memberNo}'>
<a href='list.do'>목록</a>
</form>


<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
