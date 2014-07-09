<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<title></title>
</head>
<body>
	<div align="center">
		<!-- Head -->
		<%@include file="form/head.jsp"%>
		<!-- end head -->
		<table width="80%">
			<tr>
				<td width="50%" valign="top">
					<!-- Notification list --> <c:choose>
						<c:when test="${not empty meets }">
							<c:forEach var="meet" items="${meets}">
								<%@include file="form/notification_forms/new_meet_form.jsp"%>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<spring:message code="lable.empty" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>

	</div>
</body>
</html>