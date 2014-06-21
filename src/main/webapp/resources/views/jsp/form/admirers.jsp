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
<div class="following" id="block">
	<spring:message code="lable.admirers" />
	<br />
	<c:choose>
		<c:when test="${not empty admirers}">
			<c:forEach var="user" items="${admirers}">
				<%@include file="user_form.jsp"%>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<spring:message code="lable.empty" />
		</c:otherwise>
	</c:choose>
</div>









