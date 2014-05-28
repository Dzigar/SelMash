<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Iterator"%>
<%@page import="javax.swing.text.StyledEditorKit.ItalicAction"%>
<%@page import="com.selfmash.model.Estimation"%>
<%@page import="com.selfmash.service.EstimationService"%>
<%@page import="org.hibernate.Hibernate"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.selfmash.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

</head>
<body>
	<%@include file="form/head.jsp"%>
	<table width="80%">
		<tr>
			<td width="20%" rowspan="2"><c:choose>
					<c:when test="${not empty userList }">
						<c:forEach var="user" items="${userList}">
							<a href="/${user.login}"> <c:out
									value="${user.name} ${user.lastname}" /></a>
							<br>
						</c:forEach>
					</c:when>

				</c:choose> <c:choose>
					<c:when test="${not empty Estimations }">
						<c:forEach var="estimation" items="${Estimations}">
							<a href="/${estimation.user.login}"><c:out
									value="${estimation.user.name}" /></a>
							<c:out value=" ${estimation.estimation}"></c:out>
							<br>
						</c:forEach>
					</c:when>

				</c:choose></td>
			<td width="80%" align="center">
				<table width="100%">
					<tr>
						<td width="100%" colspan="4"><img
							alt="<c:out value="${photo.title}" />"
							src="<c:out value="/resources/selfshots/${login}/${photo.title}" />"
							width="100%">
					</tr>
					<!-- Footer photo -->
					<tr>
						<td><form:form action="/photo/${photo.id}" method="POST">
								<div align="center">
									<spring:message code="lable.appreciate" />
									<input type="text" name="estimation" /> <input type="submit"
										value="Ok" />
								</div>
							</form:form></td>

						<td><spring:message code="lable.number.evaluations" />
							${photo.fans.size()}</td>
						<td><spring:message code="lable.average.rating" />
							${photo.averageRating}</td>
						<td><c:choose>
								<c:when test="${authentication eq login}">
									<form:form action="/photo/makeProfile/${photo.id}"
										method="POST">
										<div align="center">
											<input type="submit"
												value='<spring:message code="lable.make.profile" />' />
										</div>
									</form:form>

									<form:form action="/photo/delete/${photo.id}" method="POST">
										<div align="center">
											<input type="submit"
												value='<spring:message code="lable.delete" />' />
										</div>
									</form:form>
								</c:when>
							</c:choose></td>
					</tr>

					<!-- end footer -->
				</table>
			</td>
		</tr>
	</table>
</body>
</html>


