<%@page import="java.util.Iterator"%>
<%@page import="javax.swing.text.StyledEditorKit.ItalicAction"%>
<%@page import="org.hibernate.Hibernate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.selfmash.model.User"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table>
	<tr>
		<td width="50" rowspan="2"><c:choose>
				<c:when test="${not empty user.profilePhoto}">
					<img
						src="<c:out value="/resources/selfshots/${user.login}/${user.profilePhoto.title}" />"
						width="30" height="30" alt="" />
				</c:when>
				<c:otherwise>
					<img
						src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
						width="30" height="30" alt="" />
				</c:otherwise>
			</c:choose></td>
		<td><a href="/${user.login}"> <c:out
					value="${user.name} ${user.lastname}" /></a></td>
	</tr>
</table>










