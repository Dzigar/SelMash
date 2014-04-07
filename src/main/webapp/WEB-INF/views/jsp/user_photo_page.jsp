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
	<div align="center">
		<table width="100%">
			<tr>
				<td width="80%" align="center" colspan="2">
					<div class="head">
						<table width="80%">
							<tr width="80%">
								<td><a href="/index">SelfMash</a></td>
								<td width="20%" align="left"><a href="/${username}"><c:out
											value="Home"></c:out></a></td>
								<td align="right">
									<ul class="hr">
										<li><a href="">Settings</a></li>
										<li><a href="<c:url value="j_spring_security_logout" />">Logout</a></li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
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
								alt="<c:out value="${photo.name}" />"
								src="<c:out value="/resources/selfshots/user${userId}/${photo.name}" />"
								width="100%">
						</tr>
						<tr>
							<td><form:form action="/${login}/photo/${photo.id}"
									method="POST">
									<div align="center">
										<c:out value="Estimation:" />
										<input type="text" name="estimation" /> <input type="submit"
											value="Ok" />
									</div>
								</form:form></td>

							<td><c:out value="К-ство оценок: ${photo.fans.size()}" /></td>
							<td><c:out value="Средняя оценка: ${photo.averageRating}" /></td>
							<td><c:choose>
									<c:when test="${login == username }">
										<form:form action="/${login}/photo/delete/${photo.id}"
											method="POST">
											<div align="center">
												<input type="submit" value="Delete" />
											</div>
										</form:form>
									</c:when>
								</c:choose></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


