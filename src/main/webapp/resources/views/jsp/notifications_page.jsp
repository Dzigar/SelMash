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
				<td width="25%" valign="top"><%@include file="form/top.jsp"%></td>
				<td width="50%" valign="top">
					<!-- Notification list --> <c:choose>
						<c:when test="${not empty notifications }">
							<c:forEach var="notification" items="${notifications}">
								<c:choose>
									<c:when
										test="${notification.notificationMessage eq 'NEW_ADMIRER'}">
										<%@include file="form/notification_forms/new_admirer_form.jsp"%>
									</c:when>
									<c:when
										test="${notification.notificationMessage eq 'APPRECIATE_PHOTO'}">
										<%@include
											file="form/notification_forms/new_estimation_form.jsp"%>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<spring:message code="lable.empty" />
						</c:otherwise>
					</c:choose>
				</td>
				<td width="25%" valign="top">
					<!-- following --> <%@include file="form/following.jsp"%>
					<!-- end following --><%@include file="form/admirers.jsp"%></td>
			</tr>
		</table>

	</div>
</body>
</html>