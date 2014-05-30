<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<div align="left">
	<c:choose>
		<c:when test="${not empty post.user.profilePhoto}">
			<img
				src="<c:out value="/resources/selfshots/${post.user.login}/${post.user.profilePhoto.title}" />"
				width="50" height="50" />
		</c:when>
		<c:otherwise>
			<img src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
				width="50" height="50" alt="" />
		</c:otherwise>
	</c:choose>
	<a href="/${post.user.login}"> <c:out
			value="${post.user.name} ${post.user.lastname}" /></a>
	<spring:message code="action.subscribed" />
	<a href="/${post.follower.login}"> <c:out
			value="${post.follower.name}${post.follower.lastname}" /></a>
</div>