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
	<c:when test="${not empty friendList }">
		<div class="user-list" id="block">
			<c:forEach var="friend" items="${friendList}">
				<a href="/${friend.login}"> <c:out
						value="${friend.name} ${friend.lastname}" /></a>
				<br>
			</c:forEach>
		</div>
	</c:when>
</c:choose>









