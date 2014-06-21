<%@page import="java.util.Iterator"%>
<%@page import="javax.swing.text.StyledEditorKit.ItalicAction"%>
<%@page import="com.selfmash.model.Estimation"%>
<%@page import="com.selfmash.service.EstimationService"%>
<%@page import="org.hibernate.Hibernate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.selfmash.model.User"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="following" id="block">
	<spring:message code="lable.recommended" />
	<br />
	<c:choose>
		<c:when test="${not empty recommended }">
			<c:forEach var="user" items="${recommended}">
				<%@include file="user_form.jsp"%>
				<br>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<spring:message code="lable.empty" />
		</c:otherwise>
	</c:choose>
	<div align="center">
		<a href="/people"><c:out value="All" /></a>
	</div>
</div>









