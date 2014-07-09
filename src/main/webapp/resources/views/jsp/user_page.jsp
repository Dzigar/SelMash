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
		<table width="80%">
			<tr>
				<td width="25%" valign="top"><%@include file="form/top.jsp"%></td>
				<td width="50%" valign="top">
					<!-- Info about user -->
					<div id="block">
						<table width="100%">
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
								<td valign="top">
									<div style="float: left; margin-left: 5px;" align="left">
										<c:out value="${user.name} ${user.lastname}" />
									</div>
									<div style="float: right; margin-right: 10px;" align="right">
										<c:out value="${user.rating}" />
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<table style="margin-left: 0%;">
										<tr>
											<td>
												<ul style="list-style-type: none;">
													<li>Age:</li>
												</ul>
											</td>
											<td>
												<ul style="list-style-type: none;">
													<li><c:out value="${age}" /></li>
												</ul>
											</td>
										</tr>
									</table> <c:choose>
										<c:when test="${ empty meet}">
											<%@include file="form/meet_form.jsp"%>
										</c:when>
										<c:otherwise>
											<c:out value="Meet send" />
										</c:otherwise>
									</c:choose>
								</td>
							</tr>

							<tr>
								<td><%@include file="form/subscribe_form.jsp"%>
								</td>
								<td>
									<table width="100%">
										<tr>
											<td><spring:message code="lable.photo.count" />
												${photos.size()}</td>
											<td><spring:message code="lable.followers.count" />
												${userFollowers.size()}</td>
											<td><spring:message code="lable.admirers.count" />
												${userAdmirers.size()}</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</div> <!-- End info about user --> <!-- Collection user photos --> <%@include
						file="form/photo_collection.jsp"%> <!-- End collection photos -->

				</td>
				<td width="25%" valign="top">
					<!-- following   --> <%@include file="form/following.jsp"%>
					<!-- end following --> <!-- Admirers --> <%@include
						file="form/admirers.jsp"%>

				</td>
			</tr>
		</table>


	</div>
</body>
</html>