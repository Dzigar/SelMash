<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<div align="left" class="info" id="block">
	<table>
		<tr>
			<th rowspan="2"><c:forEach var="photo"
					items="${notification.sender.photos}">
					<c:choose>
						<c:when test="${photo.isAccountPhoto()}">
							<img
								src="<c:out value="/resources/selfshots/${notification.sender.login}/${photo.title}" />"
								width="50" height="50" />
						</c:when>
						<c:otherwise>
							<img
								src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
								width="100" height="100" alt="" />
						</c:otherwise>
					</c:choose>
				</c:forEach></th>
			<th><a href="/${notification.sender.login}"><c:out
						value="${notification.sender.name} ${notification.sender.lastname}" /></a></th>
		</tr>
		<tr>
			<td><spring:message code="notification.addToFriendList" /> <br>
				<form
					action="/confirmFriendship?friendId=${notification.sender.id}&notId=${notification.id}"
					method="post">
					<input type="submit"
						value="<spring:message code="lable.confirm" />"
						class="submit-button_no_style">
				</form></td>

		</tr>
	</table>
</div>