<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<div align="left">
	<c:out value="${post.user.name}" />
	<c:out value="${post.user.lastname}" />
	<spring:message code="action.add.friend" />
	<c:out value="${post.friend.name}" />
	<c:out value="${post.friend.lastname}" />
</div>