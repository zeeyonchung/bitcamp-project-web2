<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id='header' style='background-color:gray; height:60px;position:relative;'>
<div style='width:300px; height:38px; position:absolute; left:0px; top:0px;'>
<img src='../image/n.jpg' height='50' style='float:left; margin-top:6px; margin-left:8px'> 
<div style='color:white; font-weight:bold; margin-left:70px; padding-top:15px; font-family:돋움체, sans-serif; font-size:x-large;'>교육센터관리시스템</div>
</div>
<div style='height:50px; padding-top:7px; float:right; padding-right:10px;'>
<c:choose>
  <c:when test="${not empty member}">
    <img src='../upload/${photoPath}' height='45' style='vertical-align:middle'>
    <span >${member.name}</span>
    <a href='../auth/logout.do'>로그아웃</a>
  </c:when>
  <c:otherwise>
		<a href='../auth/loginform.do'>로그인</a>
  </c:otherwise>
</c:choose>
</div>
</div>