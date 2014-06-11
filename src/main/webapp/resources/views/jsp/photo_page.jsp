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
	<div align="center">
		<table width="80%">
			<tr>
				<td width="20%" rowspan="2" valign="top"><c:choose>
						<c:when test="${photo.estimations.size() > 0 }">
							<spring:message code="lable.who.appreciate" />
							<div id="block">
								<c:forEach var="estimation" items="${photo.estimations}">
									<a href="/${estimation.user.login}"><c:out
											value="${estimation.user.name}" /></a>
									<c:out value=" ${estimation.estimation}"></c:out>
									<br>
								</c:forEach>
							</div>
						</c:when>

					</c:choose></td>
				<td width="60%" align="center" valign="top">
					<table width="100%">
						<tr>
							<td width="100%" colspan="4"><img
								alt="<c:out value="${photo.title}" />"
								src="<c:out value="/resources/selfshots/${photo.user.login}/${photo.title}" />"
								width="100%">
						</tr>
						<!-- Footer photo -->
						<tr>
							<td><c:choose>
									<c:when
										test="${!isAppreciate and authentication ne photo.user.login}">
										<form:form action="/photo/appreciate/${photo.id}"
											method="POST">
											<div align="center">
												<spring:message code="lable.appreciate" />
												<input type="text" name="estimation" /> <input
													type="submit" value="Ok" />
											</div>
										</form:form>
									</c:when>
								</c:choose></td>

							<td><spring:message code="lable.number.evaluations" />
								${photo.estimations.size()}</td>
							<td><spring:message code="lable.average.rating" />
								${photo.averageRating}</td>
							<td><c:choose>
									<c:when test="${authentication eq photo.user.login}">
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
				<td width="20%"></td>
			</tr>
		</table>
	</div>

</body>
</html>


