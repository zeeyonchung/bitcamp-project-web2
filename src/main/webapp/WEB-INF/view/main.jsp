<%@page import="java.util.List"%>
<%@page import="bitcamp.java89.ems2.domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
boby {
  font-family: '맑은 고딕', sans-serif;
}
#sidemenu {
	color: white;
	padding: 10px 5px;
	float: left;
  margin: 5px 5px 5px 0px;
}

#sidemenu a {
	color: white;
	text-decoration: none;
}

#content {
  margin-left: 200px;
}

#footer {
  clear: left;
}
</style>
<meta charset='UTF-8'>
<title>${title}</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="sidemenu.jsp"></jsp:include>

	<div id="content">
		<jsp:include page="${contentPage}"></jsp:include>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
