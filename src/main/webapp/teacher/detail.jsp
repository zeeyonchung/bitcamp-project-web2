<%@page import="java.util.List"%>
<%@page import="bitcamp.java89.ems2.domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>강사 정보</h1>
<form action='update.do' method='POST' enctype='multipart/form-data'>
<table border='1'>

<tr><th>이메일</th><td><input name='email' type='text' value='${teacher.email}'></td></tr>
<tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th>이름</th><td><input name='name' type='text' value='${teacher.name}'></td></tr>
<tr><th>전화</th><td><input name='tel' type='text' value='${teacher.tel}'></td></tr>
<tr><th>홈페이지</th><td><input name='homepage' type='text' value='${teacher.homepage}'></td></tr>
<tr><th>페이스북</th><td><input name='facebook' type='text' value='${teacher.facebook}'></td></tr>
<tr><th>트위터</th><td><input name='twitter' type='text' value='${teacher.twitter}'></td></tr>
<tr><th>사진</th><td><img src='../upload/${teacher.photoList[0].filePath}' height='80'><input name='photo' type='file'></td></tr>
<tr><th>사진</th><td><img src='../upload/${teacher.photoList[1].filePath}' height='80'><input name='photo' type='file'></td></tr>
<tr><th>사진</th><td><img src='../upload/${teacher.photoList[2].filePath}' height='80'><input name='photo' type='file'></td></tr>

</table>


<button type='submit'>변경</button>
<a href='delete.do?memberNo=${teacher.memberNo}'>삭제</a>
<input name='memberNo' type='hidden' value='${teacher.memberNo}'>
<a href='list.do'>목록</a>
</form>
