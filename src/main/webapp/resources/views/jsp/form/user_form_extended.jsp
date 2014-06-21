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

<table width="100%">
	<tr>
		<td width="50" rowspan="2"><c:choose>
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
			</c:choose></td>
		<td valign="top" height="20%"><a href="/${user.login}"> <c:out
					value="${user.name} ${user.lastname}" /></a></td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<th><spring:message code="lable.rating" /></th>
					<th><spring:message code="lable.photo.count" /></th>
				</tr>
				<tr>
					<td align="center">${user.rating}</td>
					<td align="center">${user.photos.size()}</td>
				</tr>
			</table>
			<div>
				<%@include file="subscribe_form.jsp"%>
			</div>
		</td>
	</tr>
</table>









