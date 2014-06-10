<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<title></title>

</head>
<body>


	<!-- Head -->
	<%@include file="form/head.jsp"%>
	<!-- end head -->
	<div align="center">
		<table width="80%" border="0">
			<tr>
				<td width="20%" valign="top">
					<!-- following   --> <%@include file="form/following.jsp"%>
					<!-- end following -->
				</td>
				<td width="60%">
					<!-- Info about user -->
					<div id="block">
						<table width="100%" border="0">
							<tr>
								<th rowspan="2" style="width: 10%;" valign="top"><c:choose>
										<c:when test="${not empty user.profilePhoto}">
											<img
												src="<c:out value="/resources/selfshots/${user.login}/${user.profilePhoto.title}" />"
												width="100" height="100" alt="" />
										</c:when>
										<c:otherwise>
											<img
												src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
												width="100" height="100" alt="" />
										</c:otherwise>
									</c:choose></th>
								<td valign="top" height="10">
									<ul class="user-info">
										<li><c:out value="${user.name}" /></li>
										<li><c:out value="${user.lastname}" /></li>
									</ul>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<table style="margin-left: 0%;">
										<tr>
											<td>
												<ul style="list-style-type: none;">
													<li>Birth date:</li>
												</ul>
											</td>
											<td>
												<ul style="list-style-type: none;">
													<li><c:out value="${user.birthDate}" /></li>
												</ul>
											</td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td><c:choose>
										<c:when
											test="${authentication ne user.login and !isSubscribed}">
											<form
												action="/subscribe?userId=${user.id}&userLogin=${user.login}"
												method="post">
												<input type="submit"
													value='<spring:message code="lable.follow" />'
													class="submit-button_no_style">
											</form>
										</c:when>
										<c:otherwise>
											<c:if test="${authentication ne user.login}">
												<form
													action="/unsubscribe?userId=${user.id}&userLogin=${user.login}"
													method="post">
													<input type="submit"
														value='<spring:message code="lable.unsubscribe" />'>
												</form>
											</c:if>
										</c:otherwise>
									</c:choose></td>
								<td>
									<table width="100%">
										<tr>
											<td><spring:message code="lable.photo.count" />
												${photoRows.size() + 1}</td>
											<td><spring:message code="lable.followers.count" />
												${following.size()}</td>
											<td><spring:message code="lable.admirers.count" />
												${admirers.size()}</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</div> <!-- End info about user --> <!-- Collection user photos --> <%@include
						file="form/photo_collection.jsp"%> <!-- End collection photos -->

				</td>
				<td width="20%" valign="top">
					<!-- Admirers --> <%@include file="form/admirers.jsp"%></td>
			</tr>
		</table>


	</div>
</body>
</html>