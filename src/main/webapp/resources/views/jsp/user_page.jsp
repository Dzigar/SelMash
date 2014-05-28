<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<script type="text/javascript" src="/resources/js/jquery-1.3.1.js"></script>
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

		<!-- Info about user -->
		<div class="info" id="block">
			<table style="width: 100%; margin-top: 5px; margin-left: 10px;">
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
					<td>
						<!-- Add friend to friends list button --> <c:if
							test="${authentication ne user.login}">
							<c:if test="${!isFriends}">
								<form action="/addfriend?user=${user.id}&login=${user.login}"
									method="post">
									<input type="submit" value="Add friend"
										class="submit-button_no_style">
								</form>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
		<!-- End info about user -->

		<!-- Collection user photos -->
		<%@include file="form/photo_collection.jsp"%>
		<!-- End collection photos -->
	</div>
</body>
</html>