<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div align="center">
		<!-- Head -->
		<%@include file="form/head.jsp"%>
		<!-- end head -->

		<!-- users list   -->
		<%@include file="form/friends.jsp"%>
		<!-- end users list -->

		<!-- Posts -->
		<div align="left" class="info" id="block">
			<c:forEach var="post" items="${posts}">
				<c:choose>
					<c:when test="${post.action eq 'ADD_USER'}">
						<%@include file="form/post_forms/add_new_friend_form.jsp"%>
					</c:when>
				</c:choose>
				<br />
			</c:forEach>

			<!--  -->
		</div>
	</div>
</body>
</html>