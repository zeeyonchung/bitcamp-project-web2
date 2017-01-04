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
<title>매니저관리-목록</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>


<h1>매니저 정보</h1>
<a href='form.html'>추가</a><br>
  <table border='1'>
  <tr>
    <th>회원번호</th>
    <th>이름</th>
    <th>전화</th>
    <th>이메일</th>
    <th>직급</th>
    <th>팩스</th>
    <th>사진</th>
  </tr>




<c:forEach var="manager" items="${managers}">
<tr> 
	<td>${manager.memberNo}</td>
	<td><a href='detail.do?memberNo=${manager.memberNo}'>${manager.name}</a></td>
	<td>${manager.tel}</td>
	<td>${manager.email}</td>
	<td>${manager.position}</td>
	<td>${manager.fax}</td>
	<td>${manager.photoPath}</td>
</tr>
</c:forEach>

</table>


<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
