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



<c:choose>
	<c:when test="${not empty userList }">
		<c:forEach var="user" items="${userList}">
			<a href="/${user.login}"> <c:out
					value="${user.name} ${user.lastname}" /></a>
			<br>
		</c:forEach>
	</c:when>

</c:choose>


<c:choose>
	<c:when test="${not empty Estimations }">
		<c:forEach var="estimation" items="${Estimations}">
			<a href="/${estimation.user.login}"><c:out
					value="${estimation.user.name}" /></a>
			<c:out value=" ${estimation.estimation}"></c:out>
			<br>
		</c:forEach>
	</c:when>

</c:choose>
















